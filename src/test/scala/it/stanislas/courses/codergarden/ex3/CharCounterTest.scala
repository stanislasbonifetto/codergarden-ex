package it.stanislas.courses.codergarden.ex3

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class CharCounterTest extends AnyFreeSpec with ScalaCheckPropertyChecks {
  "given word Giorio" - {
    val word = "Giorgio"
    val result = CharCounter.count(Array(word))
    "should be (g, 2), (i, 2), (o, 2) (r, 1)" in {
      result should be (Map("g" -> 2, "i" -> 2, "o" -> 2, "r" -> 1))
    }
  }

  "given words Giorgio, Mario" - {
    val words = Array("Giorgio", "Mario")
    val result = CharCounter.count(words)
    """should be Map("a" -> 1, "m" -> 1, "i" -> 3, "g" -> 2, "r" -> 2, "o" -> 3)""" in {
      result should be (Map("a" -> 1, "m" -> 1, "i" -> 3, "g" -> 2, "r" -> 2, "o" -> 3))
    }
  }

  "given no words" - {
    val result = CharCounter.count(Array())
    "should be Map()" in {
      result should be (Map())
    }
  }

  "given any Array of words the sum of the result values should be the sum of the word length" in {
    forAll { (words: Array[String]) =>
      val result = CharCounter.count(words)
        result.values.sum should be (words.map(_.length).sum)
    }
  }
}
