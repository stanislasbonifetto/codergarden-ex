package it.stanislas.courses.codergarden.ex1.sol3

object Application extends App {
  val minShortestWordLength = 2

  val argsList = args.toList

  val filteredWords = filterMutableShortestAndLongestWords(argsList)

  println(s"""shortest: ${filteredWords.shortest}""")
  println(s"""longest: ${filteredWords.longest}""")

  def filterMutableShortestAndLongestWords(words: List[String]): FilteredWords = {
    var shortest: List[String] = List()
    var longest: List[String] = List()
    for (word <- words) {
      val wordLength = word.length
      if (wordLength > minShortestWordLength) {
        if (shortest.isEmpty || wordLength < shortest.head.length)
          shortest = List(word)
        else if (wordLength == shortest.head.length)
          shortest = word :: shortest

        if (longest.isEmpty || wordLength > longest.head.length)
          longest = List(word)
        else if (wordLength == longest.head.length)
          longest = word :: longest
      }
    }
    FilteredWords(shortest, longest)
  }
}

case class FilteredWords(shortest: List[String], longest: List[String])