package it.stanislas.courses.codergarden.ex1.sol4

case class FilterWords(minShortestWordLength: Int = 2) {

  def filterShortestAndLongestWords(filteredWords: FilteredWords = FilteredWords(List(), List()), words: List[String]): FilteredWords = words match {
    case Nil => filteredWords
    case head :: tail => filterShortestAndLongestWords(filterWord(filteredWords, head), tail)
  }

  private def filterWord(filteredWords: FilteredWords, word: String): FilteredWords = {
    val shortest = accumulateShortestWords(filteredWords.shortest, word)
    val longest = accumulateLongestWords(filteredWords.longest, word)
    FilteredWords(shortest, longest)
  }

  private def accumulateShortestWords(acc : List[String], word: String) : List[String] = {
    val wordLength = word.length
    if (wordLength > minShortestWordLength)
      if (acc.isEmpty || wordLength < acc.head.length)
        List(word)
      else if (wordLength == acc.head.length)
        word :: acc
      else
        acc
    else
      acc
  }

  private def accumulateLongestWords(acc : List[String], word: String) : List[String] = {
    val wordLength = word.length
    if (acc.isEmpty || wordLength > acc.head.length)
      List(word)
    else if (wordLength == acc.head.length)
      word :: acc
    else
      acc
  }
}

case class FilteredWords(shortest: List[String], longest: List[String])
