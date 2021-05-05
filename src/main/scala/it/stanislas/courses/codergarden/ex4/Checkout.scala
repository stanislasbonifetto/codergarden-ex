package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val productPrices: Map[String, Pound] = HashMap(
    "Apple" -> Pound(0.6),
    "Orange" -> Pound(0.25)
  )

  def calculateTotal(basket: List[String]) : Pound = basket match {
    case Nil => Pound(0)
    case basket => basket.foldLeft(Pound(0))(accumulateTotal)
  }

  private def accumulateTotal(total: Pound, item: String) = total + productPrices.getOrElse(item, Pound(0))
}