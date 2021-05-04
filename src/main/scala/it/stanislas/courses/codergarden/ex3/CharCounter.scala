package it.stanislas.courses.codergarden.ex3

object CharCounter {

  def count(words: Array[String]) : Map[String, Int] =
    words.toSeq
      .flatMap(toLowerSingleChar)
      .groupMapReduce(identity)(_ => 1)(_ + _)

  private def toLowerSingleChar(word: String) : Seq[String] =
    word.
      toLowerCase
      .split("")
      .filter(_ != "")
      .toSeq
}
