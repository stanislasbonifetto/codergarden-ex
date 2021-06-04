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
      mapWithElement should not be (expirableMap)
    }
    "can get an element and a new map" in {
      val key = "key"
      val value = "value"
      val mapWithElement = expirableMap.add(key, value)
      val (maybeElement, newMap) = mapWithElement.get(key)
      maybeElement should be(Some(value))
      newMap should not be (mapWithElement)
    }
    "can get an element and the ttl is updated" - {}
    "can't get an element expired" - {}
    "can't get an element not added" - {}
    "can evict element expired" - {}
  }
}

class TestClock(times: Instant*) extends Clock {
  private var currentIndex = 0
  override def now(): Instant = {
    assert(currentIndex < times.size, "to many calls")
    val time = times(currentIndex)
    currentIndex += 1
    time
  }
}
