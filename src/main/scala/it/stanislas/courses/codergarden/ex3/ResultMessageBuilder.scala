package it.stanislas.courses.codergarden.ex3

object ResultMessageBuilder {
  def build(countedChars: Map[String, Int]) : String = countedChars.map{case(k,v) => s"($k, $v)"}.mkString(", ")
}
