package it.stanislas.courses.codergarden.ex2

object Application {

  def main(args: Array[String]): Unit = {
    val argsList = args.toList
    val wordsFilter = WordsFilter()
    val wordsFiltered = wordsFilter.filterShortestAndLongest(words = argsList)
    val resultMessage = PrintWordsFilter.buildMessage(wordsFiltered)
    println(resultMessage)
  }

}
