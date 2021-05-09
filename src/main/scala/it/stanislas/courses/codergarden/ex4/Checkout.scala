package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val productPrices: Map[String, Pound] = HashMap(
    "Apple" -> Pound(0.6),
    "Orange" -> Pound(0.25),
    "Banana" -> Pound(0.20)
  )

  def calculateTotal(basket: List[String]) : Pound = basket match {
    case Nil => Pound(0)
    case basket => {
      val groupedProduct = basket.groupBy(identity).map{ case(product, items) => (product, items.size) }
      val groupedProductRemovedBundle = removeBundle(groupedProduct)
      val total = groupedProductRemovedBundle.map{ case(p, q) => calculatePrice(p, q) }.foldLeft(Pound(0))(_ + _)
      total
    }
  }

  private def removeBundle(groupedProducts: Map[String, Int]): Map[String, Int] = {
    val maybeBananas = groupedProducts.get("Banana")
    val maybeApples = groupedProducts.get("Apple")
    (maybeBananas, maybeApples)  match {
      case (Some(bananas), Some(apples)) => {
        val bananasToPay = bananas - apples
        val updatedProducts = groupedProducts + ("Banana" -> bananasToPay)
          updatedProducts
      }
      case _ => groupedProducts
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
      case "Apple" => discountTwoForOne(price, quantity)
      case "Banana" => discountTwoForOne(price, quantity)
      case "Orange" => discountTreeForTwo(price, quantity)
      case _ => Pound(0)
    }
  }
  private def discountTwoForOne(price: Pound, quantity: Int): Pound = {
    val quotient : Int = quantity / 2
    quotient * price
  }
  private def discountTreeForTwo(price: Pound, quantity: Int): Pound = {
    val quotient : Int = quantity / 3
    quotient * price
  }
}