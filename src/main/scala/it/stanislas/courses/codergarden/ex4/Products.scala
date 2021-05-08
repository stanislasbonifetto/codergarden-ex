package it.stanislas.courses.codergarden.ex4

case class Product(name: String)
object Apple extends Product("Apple")
object Orange extends Product("Orange")

case class ProductData(product: Product, price: Pound = Pound(0), promotion: Promotion = NoPromo())

sealed trait Promotion {
  def calculateDiscount(quantity: Int) : Pound
}
case class TwoForOne(productPrice: Pound) extends Promotion {
  override def calculateDiscount(quantity: Int): Pound = {
    val quotient : Int = quantity / 2
    quotient * productPrice
  }
}
case class TreeForTwo(productPrice: Pound) extends Promotion {
  override def calculateDiscount(quantity: Int): Pound = {
    val quotient : Int = quantity / 3
    quotient * productPrice
  }
}
case class NoPromo() extends Promotion {
  override def calculateDiscount(quantity: Int): Pound = Pound(0)
}
