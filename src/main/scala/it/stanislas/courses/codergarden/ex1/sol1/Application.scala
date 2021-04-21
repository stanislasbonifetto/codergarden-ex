package it.stanislas.courses.codergarden.ex1.sol1

object Application extends App {
  val minShortestWordLength = 2

  val argsList = args.toList
  val shortest = getShortestWords(argsList)
  val longest = getLongestWords(argsList)
  println(s"""shortest: $shortest""")
  println(s"""longest: $longest""")

  def getShortestWords(words: List[String]): String = words.reduce((x, y) => if (x.length > minShortestWordLength && x.length < y.length) x else y)

  def getLongestWords(words: List[String]): String = words.reduce((x, y) => if (x.length > y.length) x else y)

}
