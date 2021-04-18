package it.stanislas.courses.codergarden.ex1.sol4

object Application extends App {

  val argsList = args.toList
  val filterWords = WordsFilter()
  val filteredWords = filterWords.shortestAndLongest(words = argsList)

  println(s"""shortest: ${filteredWords.shortest}""")
  println(s"""longest: ${filteredWords.longest}""")

}

