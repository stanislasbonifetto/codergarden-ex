package it.stanislas.courses.codergarden.ex2

class WordsFilter(minShortestWordLength: Int) {

  def filterShortestAndLongest(words: List[String]): WordsFiltered = {
    words
      .foldLeft(WordsFiltered())(accumulateWord)
  }

  private def accumulateWord(wordsFiltered: WordsFiltered, word: String): WordsFiltered = {
    word.length match {
      case length if length > minShortestWordLength =>{
        val shortestWords = accumulateShortest(wordsFiltered.shortest, word)
        val longestWords = accumulateLongest(wordsFiltered.longest, word)
        WordsFiltered(shortestWords, longestWords)
      }
      case _ => wordsFiltered
    }
  }

  private def accumulateShortest(acc : WordsSameLength, word: String) :WordsSameLength = accumulateBy(_ < _, acc, word)

  private def accumulateLongest(acc : WordsSameLength, word: String) :WordsSameLength = accumulateBy(_ > _, acc, word)

  private def accumulateBy(lengthComparator: (Int, Int) => Boolean, acc : WordsSameLength, word: String) : WordsSameLength = {
    acc match {
      case WordsSameLength(List(), 0) => WordsSameLength(List(word), word.length)
      case WordsSameLength(_, wordLength) if lengthComparator(word.length, wordLength) => WordsSameLength(List(word), word.length)
      case WordsSameLength(words, wordLength) if word.length == wordLength => WordsSameLength(word :: words, word.length)
      case _ => acc
    }
  }
}

object WordsFilter {
  def apply(minShortestWordLength: Int = 2) : WordsFilter = new WordsFilter(minShortestWordLength)
}

case class WordsFiltered(shortest: WordsSameLength = WordsSameLength(), longest: WordsSameLength = WordsSameLength())

case class WordsSameLength(words: List[String] = List()) {
  val wordLength : Int = words.headOption.getOrElse("").length
  def append(word: String) = word.length match {
    case length == wordLength => WordsSameLength(word :: words)
    case _ => WordsSameLength(List(word))
  }
}