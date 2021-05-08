package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val productsDataMap: Map[Product, ProductData] = HashMap(
    Apple -> ProductData(Apple, Pound(0.6), TwoForOne(Pound(0.6))) ,
    Orange -> ProductData(Orange, Pound(0.25), TreeForTwo(Pound(0.6)))
  )

  def calculateTotal(basket: List[Product]) : Pound = basket match {
    case Nil => Pound(0)
    case basket => {
      val basketTotal = basket.foldLeft(BasketTotal())(accumulateTotal)
      basketTotal.discountedTotal
    }
  }

  private def accumulateTotal(basketTotal: BasketTotal, item: Product) = {
    val productData = productsDataMap.getOrElse(item, ProductData(item))
    basketTotal.addProduct(productData)
  }
}