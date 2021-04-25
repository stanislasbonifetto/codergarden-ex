package it.stanislas.courses.codergarden.ex2

class WordsFilter(val minShortestWordLength: Int) {

  def shortestAndLongest(wordsFiltered: WordsFiltered = WordsFiltered(List(), List()), words: List[String]): WordsFiltered = words match {
    case Nil => wordsFiltered
    case head :: tail => shortestAndLongest(filterWord(wordsFiltered, head), tail)
  }

  private def filterWord(wordsFiltered: WordsFiltered, word: String): WordsFiltered = {
    val shortestWords = filterShortest(wordsFiltered.shortest, word)
    val longestWords = filterLongest(wordsFiltered.longest, word)
    WordsFiltered(shortestWords, longestWords)
  }

  private def filterShortest(acc : List[String], word: String) :List[String] = filter(_ < _, acc, word)

  private def filterLongest(acc : List[String], word: String) :List[String] = filter(_ > _, acc, word)

  private def filter(comparator: (Int, Int) => Boolean, acc : List[String], word: String) : List[String] = {
    val wordLength = word.length
    if (wordLength > minShortestWordLength)
      if (acc.isEmpty || comparator(wordLength, acc.head.length))
        List(word)
      else if (wordLength == acc.head.length)
        word :: acc
      else
        acc
    else
      acc
  }
}

object WordsFilter {
  def apply(minShortestWordLength: Int = 2) : WordsFilter = new WordsFilter(minShortestWordLength)
}

case class WordsFiltered(shortest: List[String], longest: List[String])
