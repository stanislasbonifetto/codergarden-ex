package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val productPrices: Map[String, Pound] = HashMap(
    "Apple" -> Pound(0.6),
    "Orange" -> Pound(0.25)
  )

  def calculateTotal(basket: List[String]) : Pound = basket match {
    case Nil => Pound(0)
    case basket => {
      val groupedProduct = basket.groupBy(identity).view.mapValues(_.size)
      val total = groupedProduct.map{ case(p, q) => calculatePrice(p, q) }.foldLeft(Pound(0))(_ + _)
      total
    }
  }

  private def calculatePrice(product: String, quantity: Int): Pound = {
    val productPrice = productPrices.getOrElse(product, Pound(0))
    val productTotal = productPrice * quantity
    val discount = calculateDiscount(product, productPrice, quantity)
    productTotal - discount
  }

  private def calculateDiscount(product: String, price: Pound, quantity: Int): Pound = {
    product match {
      case "Apple" => {
        val quotient : Int = quantity / 2
        quotient * price
      }
      case "Orange" => {
        val quotient : Int = quantity / 3
        quotient * price
      }
      case _ => Pound(0)
    }
  }
}