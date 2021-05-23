package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

object Checkout {
  val appleProductPrice = ProductPrice(Apple, Pound(0.6))
  val orangeProductPrice = ProductPrice(Orange, Pound(0.25))

  val productPricesMap: Map[Product, ProductPrice] = HashMap(
    Apple -> appleProductPrice,
    Orange -> orangeProductPrice
  )

  val promotions: List[Promotion] = List(
    TwoForOne(appleProductPrice),
    TreeForTwo(orangeProductPrice)
  )

  def calculateTotal(products: List[Product]): Money =
    products match {
      case Nil => Pound(0)
      case products => {
        val basket = toBasket(products)
        val discountedBasket =
          promotions.foldLeft(basket)((b: Basket, p: Promotion) =>
            p.applyDiscount(b)
          )
        discountedBasket.total
      }
    }

  private def toBasket(products: List[Product]): Basket = {
    val lines: Map[Product, Line] = products
      .groupBy(identity)
      .map {
        case (k, v) =>
          k -> Line(
            productPricesMap.getOrElse(k, ProductPrice(NoProduct)),
            v.size
          )
      }
    Basket(lines)
  }
}
