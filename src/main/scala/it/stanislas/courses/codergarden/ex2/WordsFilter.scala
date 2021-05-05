package it.stanislas.courses.codergarden.ex2

class WordsFilter(minShortestWordLength: Int) {

  def filterShortestAndLongest(words: List[String]): WordsFiltered = {
    words
      .foldLeft(WordsFiltered())(accumulateWord)
  }

  private def accumulateWord(wordsFiltered: WordsFiltered, word: String): WordsFiltered = word.length match {
    case wordLength if wordLength > minShortestWordLength => WordsFiltered(wordsFiltered.shortest.append(word), wordsFiltered.longest.append(word))
    case _ => wordsFiltered
  }
}

object WordsFilter {
  def apply(minShortestWordLength: Int = 2) : WordsFilter = new WordsFilter(minShortestWordLength)
}