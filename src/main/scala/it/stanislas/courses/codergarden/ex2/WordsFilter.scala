package it.stanislas.courses.codergarden.ex2

class WordsFilter(minShortestWordLength: Int) {

  def filterShortestAndLongest(words: List[String]): WordsFiltered = {
    words
      .filter(_.length > minShortestWordLength)
      .foldLeft(WordsFiltered())(accumulateWord(_, _))
  }

  private def accumulateWord(wordsFiltered: WordsFiltered, word: String): WordsFiltered = {
    val shortestWords = accumulateShortest(wordsFiltered.shortest, word)
    val longestWords = accumulateLongest(wordsFiltered.longest, word)
    WordsFiltered(shortestWords, longestWords)
  }

  private def accumulateShortest(acc : List[String], word: String) :List[String] = accumulateBy(_ < _, acc, word)

  private def accumulateLongest(acc : List[String], word: String) :List[String] = accumulateBy(_ > _, acc, word)

  private def accumulateBy(lengthComparator: (Int, Int) => Boolean, acc : List[String], word: String) : List[String] = {
      if (acc.isEmpty || lengthComparator(word.length, acc.head.length))
        List(word)
      else if (word.length == acc.head.length)
        word :: acc
      else
        acc
  }
}

object WordsFilter {
  def apply(minShortestWordLength: Int = 2) : WordsFilter = new WordsFilter(minShortestWordLength)
}

case class WordsFiltered(shortest: List[String] = List(), longest: List[String] = List())