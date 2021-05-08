package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

case class BasketTotal(appliedPromotions: Map[Product, AppliedPromotion] = HashMap(), total: Pound = Pound(0)){
  def discount : Pound = appliedPromotions.values.map(_.discount).foldLeft(Pound(0))(_ + _)
  def discountedTotal : Pound = total - discount

  def addProduct(productData: ProductData): BasketTotal = {
    val existingAppliedPromo = appliedPromotions.getOrElse(productData.product ,AppliedPromotion(productData.promotion))
    val appliedPromo = existingAppliedPromo.addQuantity(1)
    val newTotal = total + productData.price
    BasketTotal(appliedPromotions + (productData.product -> appliedPromo), newTotal)
  }
}

case class AppliedPromotion(promotion: Promotion, quantity: Int = 0) {
  def discount: Pound = promotion.calculateDiscount(quantity)
  def addQuantity(quantity: Int) = AppliedPromotion(this.promotion, this.quantity + quantity)
}
