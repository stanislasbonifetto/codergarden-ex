package it.stanislas.courses.codergarden.ex3

object Application {

  def main(args: Array[String]): Unit = {
    val countedChars = CharCounter.count(args)
    val resultMessage = ResultMessageBuilder.build(countedChars)
    println(resultMessage)
  }

}