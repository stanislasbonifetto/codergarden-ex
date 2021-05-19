package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val appleProductPrice = ProductPrice(Apple, Pound(0.6))
  val orangeProductPrice = ProductPrice(Orange, Pound(0.25))

  val productsDataMap: Map[Product, ProductData] = HashMap(
    Apple -> ProductData(appleProductPrice, TwoForOne(appleProductPrice)),
    Orange -> ProductData(orangeProductPrice, TreeForTwo(orangeProductPrice))
  )

  val promotions: List[Promotion] = List(
    TwoForOne(appleProductPrice),
    TreeForTwo(orangeProductPrice)
  )

  def calculateTotal(basket: List[Product]): Money =
    basket match {
      case Nil => Pound(0)
      case basket => {
        val basketTotal = basket.foldLeft(BasketTotal())(accumulateTotal)
        basketTotal.discountedTotal
      }
    }

  private def accumulateTotal(basketTotal: BasketTotal, item: Product) = {
    val productData =
      productsDataMap.getOrElse(item, ProductData(ProductPrice(item)))
    basketTotal.addProduct(productData)
  }
}
