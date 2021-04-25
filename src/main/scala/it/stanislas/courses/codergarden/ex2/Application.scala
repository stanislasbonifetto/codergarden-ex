package it.stanislas.courses.codergarden.ex2

object Application {

  def main(args: Array[String]): Unit = {
    val argsList = args.toList
    val filterWords = WordsFilter()
    val filteredWords = filterWords.shortestAndLongest(words = argsList)

    val shortestWords = filteredWords.shortest.mkString(", ")
    val longestWords = filteredWords.longest.mkString(", ")

    val shortestWordLength = lengthOfHead(filteredWords.shortest)
    val longestWordLength = lengthOfHead(filteredWords.longest)

    println(s"""shortest words are length $shortestWordLength: ${shortestWords}""")
    println(s"""longest words are length $longestWordLength: ${longestWords}""")

  }

  def lengthOfHead(list: List[String]) = list.headOption.getOrElse("").length
}
