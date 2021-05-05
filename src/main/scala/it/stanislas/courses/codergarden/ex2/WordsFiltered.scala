package it.stanislas.courses.codergarden.ex2

case class WordsFiltered(
                          shortest: WordsSameLength = ShortestWordsSameLength,
                          longest: WordsSameLength = LongestWordsSameLength
                        )

object ShortestWordsSameLength extends WordsSameLength(lengthComparator = _ < _)
object LongestWordsSameLength extends WordsSameLength(lengthComparator = _ > _)

case class WordsSameLength(words: List[String] = List(), lengthComparator: (Int, Int) => Boolean) {
  val wordLength : Int = words.headOption.getOrElse("").length
  def append(word: String): WordsSameLength = {
    words match {
      case List() => WordsSameLength(List(word), this.lengthComparator)
      case _ => {
        word.length match {
          case length if lengthComparator(length, wordLength) => WordsSameLength(List(word), lengthComparator)
          case length if length == wordLength => WordsSameLength(word :: words, this.lengthComparator)
          case _ => this
        }
      }
    }
  }
}