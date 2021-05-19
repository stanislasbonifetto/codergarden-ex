package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

case class BasketTotal(appliedPromotions: Map[Product, AppliedPromotion] = HashMap(), total: Money = Pound(0)){
  def discount : Money = appliedPromotions.values.map(_.discount).foldLeft(Pound(0): Money)(_ + _)
  def discountedTotal : Money = total - discount

  def addProduct(productData: ProductData): BasketTotal = {
    val existingAppliedPromo = appliedPromotions.getOrElse(productData.productPrice.product ,AppliedPromotion(productData.promotion))
    val appliedPromo = existingAppliedPromo.addQuantity(1)
    val newTotal = total + productData.productPrice.price
    BasketTotal(appliedPromotions + (productData.productPrice.product -> appliedPromo), newTotal)
  }
}

case class AppliedPromotion(promotion: Promotion, quantity: Int = 0) {
  def discount: Money = promotion.calculateDiscount(quantity)
  def addQuantity(quantity: Int) = AppliedPromotion(this.promotion, this.quantity + quantity)
}
