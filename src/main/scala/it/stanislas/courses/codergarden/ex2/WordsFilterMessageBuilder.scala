package it.stanislas.courses.codergarden.ex2

object WordsFilterMessageBuilder {

  def build(wordsFiltered: WordsFiltered): String = {
    val shortestWordsByComma = wordsFiltered.shortest.words.mkString(", ")
    val longestWordsByComma = wordsFiltered.longest.words.mkString(", ")

    val shortestWordLength = wordsFiltered.shortest.wordLength
    val longestWordLength = wordsFiltered.longest.wordLength

    val output = s"""shortest words are length $shortestWordLength: ${shortestWordsByComma}
         |longest words are length $longestWordLength: ${longestWordsByComma}""".stripMargin
    output
  }
}