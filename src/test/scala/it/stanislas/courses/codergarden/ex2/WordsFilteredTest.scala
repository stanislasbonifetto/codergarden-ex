package it.stanislas.courses.codergarden.ex2

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}


class WordsFilteredTest extends AnyFreeSpec {
  "A WordsSameLength" - {
    val wordsSameLength = WordsSameLength(lengthComparator = _ < _)
    "with default" - {
      "words empty" in {
        wordsSameLength.words should be(empty)
      }
      "wordLength" in {
        wordsSameLength.wordLength should be(0)
      }
    }
    "with append myWord" - {
      val appended = wordsSameLength.append("myWord")
      """should different instance""" in {
        appended should not be wordsSameLength
      }
      """words should be List("myWord)""" in {
        appended.words should be(List("myWord"))
      }
      "wordLength should be 6" in {
        appended.wordLength should be(6)
      }
    }
    "with append words with same length" - {
      val appended = wordsSameLength.append("one").append("two")
      """should different instance""" in {
        appended should not be wordsSameLength
      }
      """words should be List("one", "two")""" in {
        appended.words should contain theSameElementsAs List("one", "two")
      }
      "wordLength should be 3" in {
        appended.wordLength should be(3)
      }
    }
  }

  "A ShortestWordsSameLength" - {
    "with append word shorter" - {
      val shortest = ShortestWordsSameLength.append("one")
      val appended = shortest.append("is")
      """should different instance""" in {
        appended should not be shortest
      }
      """words should be List("is")""" in {
        appended.words should contain theSameElementsAs List("is")
      }
      "wordLength should be 2" in {
        appended.wordLength should be (2)
      }
    }

    "with append word longer" - {
      val shortest = ShortestWordsSameLength.append("is")
      val appended = shortest.append("one")
      """should same instance""" in {
        appended should be (shortest)
      }
      """words should be List("is")""" in {
        appended.words should contain theSameElementsAs List("is")
      }
      "wordLength should be 2" in {
        appended.wordLength should be (2)
      }
    }
  }

  "A LongestWordsSameLength" - {
    "with append word longer" - {
      val longest = LongestWordsSameLength.append("is")
      val appended = longest.append("one")
      """should different instance""" in {
        appended should not be longest
      }
      """words should be List("is")""" in {
        appended.words should contain theSameElementsAs List("one")
      }
      "wordLength should be 3" in {
        appended.wordLength should be (3)
      }
    }
    "with append word shorter" - {
      val longest = LongestWordsSameLength.append("one")
      val appended = longest.append("is")
      """should same instance""" in {
        appended should be (appended)
      }
      """words should be List("is")""" in {
        appended.words should contain theSameElementsAs List("one")
      }
      "wordLength should be 3" in {
        appended.wordLength should be (3)
      }
    }
  }

}
