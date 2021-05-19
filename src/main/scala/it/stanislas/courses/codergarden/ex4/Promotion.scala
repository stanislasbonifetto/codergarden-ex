package it.stanislas.courses.codergarden.ex4

sealed trait Promotion {
  def calculateDiscount(quantity: Int) : Money
}

abstract class ProductQuantityPromotion(productPrice: ProductPrice) extends Promotion {
  protected def calculateProductsToDiscount(quantity: Int) : Int
  override def calculateDiscount(quantity: Int): Money = {
    val quotient = calculateProductsToDiscount(quantity)
    productPrice.price * quotient
  }
}

case class TwoForOne(productPrice: ProductPrice) extends ProductQuantityPromotion(productPrice) {
  override def calculateProductsToDiscount(quantity: Int): Int = quantity / 2

}
case class TreeForTwo(productPrice: ProductPrice) extends ProductQuantityPromotion(productPrice) {
  override def calculateProductsToDiscount(quantity: Int): Int = quantity / 3
}
case object NoPromo extends Promotion {
  override def calculateDiscount(quantity: Int): Money = Pound(0)
}
