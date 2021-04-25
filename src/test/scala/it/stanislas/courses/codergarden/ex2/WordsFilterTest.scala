package it.stanislas.courses.codergarden.ex2

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class WordsFilterTest extends AnyFreeSpec {

  "A WordsFilter shortestAndLongest" - {
    val wordsFilter = WordsFilter()
    "with an empty list" - {
      val wordsFiltered = wordsFilter.shortestAndLongest(words = List())
      "shortest empty" in {
        wordsFiltered.shortest should be (empty)
      }
      "longest empty" in {
        wordsFiltered.longest should be (empty)
      }
    }
    "with a list with of words shorter that 2" - {
      val words = List("is", "it", "a", "1", ".")
      val wordsFiltered = wordsFilter.shortestAndLongest(words = words)
      "shortest empty" in {
        wordsFiltered.shortest should be (empty)
      }
      "longest empty" in {
        wordsFiltered.longest should be (empty)
      }
    }

    """with a list with of words List("one", "two", "tree", "four")""" - {
      val words = List("one", "two", "tree", "four")
      val wordsFiltered = wordsFilter.shortestAndLongest(words = words)
      """shortest List("two", "one")""" in {
        wordsFiltered.shortest should contain theSameElementsAs List("one", "two")
      }
      """longest List("four", "tree")""" in {
        wordsFiltered.longest should contain theSameElementsAs List("tree", "four")
      }
    }
  }

}
