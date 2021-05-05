package it.stanislas.courses.codergarden.ex3

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

class ResultMessageBuilderTest extends AnyFreeSpec {
  "given countedChars Map()" - {
    val countedChars: Map[String, Int] = Map()
    "when ResultMessageBuilder.build" - {
      val message = ResultMessageBuilder.build(countedChars)
      "then message should ()" in {
        message should be("")
      }
    }
  }

  """given countedChars Map("a" -> 1)""" - {
    val countedChars : Map[String, Int] = Map("a" -> 1, "b" -> 2)
    "when ResultMessageBuilder.build" - {
      val message = ResultMessageBuilder.build(countedChars)
      "then message should ()" in {
        message should be ("(a, 1), (b, 2)")
      }
    }
  }
}
