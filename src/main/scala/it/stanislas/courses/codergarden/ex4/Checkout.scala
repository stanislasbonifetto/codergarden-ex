package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.HashMap

class Checkout(
    productPrices: Map[Product, ProductPrice],
    promotions: List[Promotion]
) {
  private val basketEngine: BasketEngine = new BasketEngine(promotions)

  def calculate(products: Product*): Basket = calculate(products.toList)

  def calculate(products: List[Product]): Basket =
    basketEngine.calculate(toBasket(products))

  private def toBasket(products: List[Product]): Basket = {
    val lines: Map[Product, Line] = products
      .groupBy(identity)
      .map {
        case (k, v) =>
          k -> Line(
            productPrices.getOrElse(k, ProductPrice(NoProduct)),
            v.size
          )
      }
    Basket(lines)
  }
}

object Checkout {
  val appleProductPrice = ProductPrice(Apple, Pound(0.6))
  val orangeProductPrice = ProductPrice(Orange, Pound(0.25))
  val bananaProductPrice = ProductPrice(Banana, Pound(0.20))

  val productPricesMap: Map[Product, ProductPrice] = HashMap(
    Apple -> appleProductPrice,
    Orange -> orangeProductPrice,
    Banana -> bananaProductPrice
  )

  val promotions: List[Promotion] = List(
    ProductBundlePromotion(Apple, Banana),
    TwoForOne(Apple),
    TwoForOne(Banana),
    TreeForTwo(Orange),
    PercentageDiscount(0.1)
  )

  def apply(
      productPrices: Map[Product, ProductPrice],
      promotions: List[Promotion]
  ): Checkout = new Checkout(productPrices, promotions)
}
