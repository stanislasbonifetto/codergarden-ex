package it.stanislas.courses.codergarden.ex5

import java.time.Instant
import scala.concurrent.duration.Duration

case class ExpirableMap[K, V](
    internalMap: Map[K, MapValue[V]],
    ttl: Duration,
    clock: Clock
) {

  def add(key: K, value: V): ExpirableMap[K, V] = {
    val newInternalMap = internalMap + (key -> MapValue(
      value,
      clock.now().plusMillis(ttl.toMillis)
    ))
    new ExpirableMap[K, V](newInternalMap, ttl, clock)
  }

  def get(key: K): (Option[V], ExpirableMap[K, V]) = {
    val maybeValue = internalMap.get(key)
    maybeValue match {
      case Some(MapValue(value, _)) => {
        val newInternalMap = internalMap + (key -> MapValue(
          value,
          clock.now().plusMillis(ttl.toMillis)
        ))
        val newMap = new ExpirableMap[K, V](newInternalMap, ttl, clock)
        (Some(value), newMap)
      }
      case _ => (None, this)
    }
  }

  def evictCache(): ExpirableMap[K, V] = ???
}

object ExpirableMap {
  def empty[K, V](ttl: Duration, clock: Clock = RealClock) =
    new ExpirableMap[K, V](Map(), ttl, clock)
}

case class MapValue[V](value: V, expiredTime: Instant)

trait Clock {
  def now(): Instant
}

object RealClock extends Clock {
  override def now(): Instant = Instant.now()
}
