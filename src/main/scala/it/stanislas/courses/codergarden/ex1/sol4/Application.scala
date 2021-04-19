package it.stanislas.courses.codergarden.ex1.sol4

object Application extends App {

  val argsList = args.toList
  val filterWords = WordsFilter()
  val filteredWords = filterWords.shortestAndLongest(words = argsList)

  val shortestWords = filteredWords.shortest.mkString(", ")
  val longestWords = filteredWords.longest.mkString(", ")

  val shortestWordLength = lengthOfHead(filteredWords.shortest)
  val longestWordLength = lengthOfHead(filteredWords.longest)

  println(s"""shortest words are length $shortestWordLength: ${shortestWords}""")
  println(s"""longest words are length $longestWordLength: ${longestWords}""")

  def lengthOfHead(list: List[String]) = list.headOption.getOrElse("").length
}

