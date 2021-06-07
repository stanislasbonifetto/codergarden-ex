package it.stanislas.courses.codergarden.ex5

import java.time.Instant
import scala.concurrent.duration.Duration

case class ExpirableMap[K, V](
    internalMap: Map[K, MapValue[V]],
    ttl: Duration,
    clock: Clock
) {

  def add(key: K, value: V): ExpirableMap[K, V] = {
    val (_, newMap) = addOrUpdate(key, value)
    newMap
  }

  def get(key: K): (Option[V], ExpirableMap[K, V]) = {
    val (maybeValue, map) = getValue(key)
    (maybeValue.map(_.value), map)
  }

  def getValue(key: K): (Option[MapValue[V]], ExpirableMap[K, V]) = {
    val maybeValue = internalMap.get(key)
    maybeValue match {
      case Some(MapValue(value, expiredTime))
          if clock.now().isBefore(expiredTime) =>
        val (newValue, newMap) = addOrUpdate(key, value)
        (Some(newValue), newMap)
      case _ => (None, this)
    }
  }

  def evictCache(): ExpirableMap[K, V] = {
    val newInternalMap =
      internalMap.filter(r => clock.now().isBefore(r._2.expiredTime))
    val newMap = newExpirableMap(newInternalMap)
    newMap
  }

  private def addOrUpdate(
      key: K,
      value: V
  ): (MapValue[V], ExpirableMap[K, V]) = {
    val newMapValue = newMapValueWithTtl(value)
    val newInternalMap = internalMap + (key -> newMapValue)
    (newMapValue, newExpirableMap(newInternalMap))
  }

  private def newMapValueWithTtl(value: V): MapValue[V] =
    MapValue(
      value,
      clock.now().plusMillis(ttl.toMillis)
    )

  private def newExpirableMap(internalMap: Map[K, MapValue[V]]) =
    new ExpirableMap[K, V](internalMap, ttl, clock)
}

object ExpirableMap {
  def empty[K, V](ttl: Duration, clock: Clock = RealClock) =
    new ExpirableMap[K, V](Map(), ttl, clock)
}

case class MapValue[V](value: V, expiredTime: Instant)
    extends Ordered[MapValue[V]] {
  override def compare(that: MapValue[V]): Int =
    this.expiredTime.compareTo(that.expiredTime)
}

trait Clock {
  def now(): Instant
}

object RealClock extends Clock {
  override def now(): Instant = Instant.now()
}
