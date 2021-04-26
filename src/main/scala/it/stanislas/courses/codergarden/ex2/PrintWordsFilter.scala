package it.stanislas.courses.codergarden.ex2

object PrintWordsFilter {

  def print(wordsFiltered: WordsFiltered): Unit = {
    val shortestWords = wordsFiltered.shortest.mkString(", ")
    val longestWords = wordsFiltered.longest.mkString(", ")

    val shortestWordLength = lengthOfHead(wordsFiltered.shortest)
    val longestWordLength = lengthOfHead(wordsFiltered.longest)

    println(s"""shortest words are length $shortestWordLength: ${shortestWords}""")
    println(s"""longest words are length $longestWordLength: ${longestWords}""")
  }

  private def lengthOfHead(list: List[String]) = list.headOption.getOrElse("").length
}
