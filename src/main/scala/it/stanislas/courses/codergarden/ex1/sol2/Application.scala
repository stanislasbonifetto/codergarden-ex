package it.stanislas.courses.codergarden.ex1.sol2

object Application extends App {
  val minShortestWordLength = 2

  val argsList = args.toList

  val filteredWords = filterMutableShortestAndLongestWords(argsList)

  println(s"""shortest: ${filteredWords.shortest}""")
  println(s"""longest: ${filteredWords.longest}""")

  def filterMutableShortestAndLongestWords(words: List[String]): FilteredWords = {
    var shortest: Option[String] = None
    var longest: Option[String] = None
    for (word <- words) {
      val wordLength = word.length
      if (wordLength > minShortestWordLength && (shortest.isEmpty || wordLength < shortest.head.length))
        shortest = Some(word)
      if (longest.isEmpty || wordLength >= longest.head.length)
        longest = Some(word)
    }
    FilteredWords(shortest, longest)
  }
}

case class FilteredWords(shortest: Option[String], longest: Option[String])