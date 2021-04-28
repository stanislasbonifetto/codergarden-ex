package it.stanislas.courses.codergarden.ex2

object PrintWordsFilter {

  def buildMessage(wordsFiltered: WordsFiltered): String = {
    val shortestWordsByComma = wordsFiltered.shortest.mkString(", ")
    val longestWordsByComma = wordsFiltered.longest.mkString(", ")

    val shortestWordLength = lengthOfHead(wordsFiltered.shortest)
    val longestWordLength = lengthOfHead(wordsFiltered.longest)

    println(s"""shortest words are length $shortestWordLength: ${shortestWordsByComma}""")
    println(s"""longest words are length $longestWordLength: ${longestWordsByComma}""")
  }

  private def lengthOfHead(list: List[String]) = list.headOption.getOrElse("").length
}
