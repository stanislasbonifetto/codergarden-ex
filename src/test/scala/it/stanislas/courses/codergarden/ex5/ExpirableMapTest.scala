package it.stanislas.courses.codergarden.ex5

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import java.time.Instant
import scala.concurrent.duration.DurationInt

class ExpirableMapTest extends AnyFreeSpec with Matchers {

  "An ExpirableMap" - {
    val expirableMap = ExpirableMap.empty[String, String](5.seconds)
    "can add and element" in {
      val mapWithElement = expirableMap.add("key", "value")
      mapWithElement should not be expirableMap
    }
    "can get an element and a new map" in {
      val key = "key"
      val value = "value"
      val mapWithElement = expirableMap.add(key, value)
      val (maybeElement, newMap) = mapWithElement.get(key)
      maybeElement should be(Some(value))
      newMap should not be mapWithElement
    }
    "can't get an element not added" in {
      val (maybeElement, newMap) = expirableMap.getValue("key")
      maybeElement should be(None)
      newMap should be(expirableMap)
    }
    "can get an element and the ttl is updated" in {
      val key = "key"
      val value = "value"
      val mapWithElement = expirableMap.add(key, value)
      val (Some(get1), _) = mapWithElement.getValue(key)
      val (Some(get2), _) = mapWithElement.getValue(key)
      get2.value should be(get1.value)
      get2.expiredTime should not be get1.expiredTime
    }
  }
  "An ExpirableMap with a mockClock" - {
    val ttl = 1.seconds
    val now = Instant.now()
    val expiredTime = now.minusSeconds(ttl.toSeconds)
    val mockClock = new MockClock(expiredTime, now)
    val expirableMap = ExpirableMap.empty[String, String](ttl, mockClock)
    "can't get an element expired" in {
      val key = "key"
      val value = "value"
      val mapWithElement = expirableMap.add(key, value)
      val (maybeElement, newMap) = mapWithElement.get(key)
      maybeElement should be(None)
      newMap should be(mapWithElement)
    }
  }

  "An ExpirableMap with a mockClock 1" - {
    val ttl = 1.seconds
    val now = Instant.now()
    val expiredTime = now.minusSeconds(ttl.toSeconds)
    val mockClock = new MockClock(expiredTime, now)
    val expirableMap = ExpirableMap.empty[String, String](ttl, mockClock)
    "can evict element expired" in {
      val mapWithElement = expirableMap.add("key", "value")
      val newMap = mapWithElement.evictCache()
      newMap should not be mapWithElement
    }
  }
}

class MockClock(times: Instant*) extends Clock {
  private var currentIndex = 0
  override def now(): Instant = {
    assert(currentIndex < times.size, "to many calls")
    val time = times(currentIndex)
    currentIndex += 1
    time
  }
}
