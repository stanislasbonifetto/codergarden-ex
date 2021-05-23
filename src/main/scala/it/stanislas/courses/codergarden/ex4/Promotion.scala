package it.stanislas.courses.codergarden.ex4

sealed trait Promotion {
  def applyDiscount(basket: Basket): Basket
}

abstract class ProductQuantityPromotion(productPrice: ProductPrice)
    extends Promotion {
  protected def calculateProductsToDiscount(quantity: Int): Int
  def calculateDiscount(quantity: Int): Discount = {
    val quotient = calculateProductsToDiscount(quantity)
    Discount(this, productPrice.price * quotient)
  }

  override def applyDiscount(basket: Basket): Basket = {
    basket match {
      case Basket(List()) => basket
      case Basket(lines)  => Basket(applyDiscounts(lines))
    }
  }

  def applyDiscounts(lines: Map[Product, Line]): Map[Product, Line] = {
    val mayBeProductLineToApply = lines.get(productPrice.product)
    mayBeProductLineToApply match {
      case Some(line) =>
        val newLine = line.copy(discounts =
          calculateDiscount(line.quantity) :: line.discounts
        )
        lines + (line.productPrice.product -> newLine)
      case None => lines
    }
  }
}

case class TwoForOne(productPrice: ProductPrice)
    extends ProductQuantityPromotion(productPrice) {
  override def calculateProductsToDiscount(quantity: Int): Int = quantity / 2
}
case class TreeForTwo(productPrice: ProductPrice)
    extends ProductQuantityPromotion(productPrice) {
  override def calculateProductsToDiscount(quantity: Int): Int = quantity / 3
}

object NoPromo extends Promotion {
  override def applyDiscount(basket: Basket): Basket = basket
}

case class Discount(promotion: Promotion, value: Money)
