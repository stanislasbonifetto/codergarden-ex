package it.stanislas.courses.codergarden.ex2

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class WordsFilterMessageBuilderTest extends AnyFreeSpec {
  "A WordsFilterMessageBuilderTest" - {
    val builder = WordsFilterMessageBuilder
    "with empty results" - {
      val message = builder.build(WordsFiltered())
      "should be" in {
        message should be (s"""shortest words are length 0: \nlongest words are length 0: """)
      }
    }
    "with single word results" - {
      val message = builder.build(
        WordsFiltered(
          shortest = ShortestWordsSameLength.append("one"),
          longest = LongestWordsSameLength.append("one")
        ))
      "should be" in {
        message should be (s"""shortest words are length 3: one\nlongest words are length 3: one""")
      }
    }
    "with multiple words results" - {
      val message = builder.build(
        WordsFiltered(
          shortest = ShortestWordsSameLength.append("one").append("two"),
          longest = LongestWordsSameLength.append("one").append("two")
        ))
      "should be" in {
        message should be (s"""shortest words are length 3: two, one\nlongest words are length 3: two, one""")
      }
    }
  }

}
