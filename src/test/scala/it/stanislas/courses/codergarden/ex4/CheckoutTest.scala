package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.immutable.HashMap

class CheckoutTest extends AnyFreeSpec with Matchers {
  "Given a list of products" - {
    val apple = Product("Apple")
    val orange = Product("Orange")
    val banana = Product("Banana")
    "and prices" - {
      val appleProductPrice = ProductPrice(apple, Pound(0.6))
      val orangeProductPrice = ProductPrice(orange, Pound(0.25))
      val bananaProductPrice = ProductPrice(banana, Pound(0.20))

      val productPrices: Map[Product, ProductPrice] = HashMap(
        apple -> appleProductPrice,
        orange -> orangeProductPrice,
        banana -> bananaProductPrice
      )
      "and list of promotions" - {
        val promotions: List[Promotion] = List(
          ProductBundlePromotion(Apple, Banana),
          TwoForOne(Apple),
          TreeForTwo(Orange),
          TwoForOne(Banana)
        )
        "and a Checkout" - {
          val checkout = Checkout(productPrices, promotions)
          "and a basket with" - {
            val products = List(
              Product("Apple"),
              Product("Apple"),
              Product("Orange"),
              Product("Apple")
            )
            "when calculate the basket" - {
              val basket = checkout.calculate(products)
              "then 1 Apple discount" in {
                val discountsCount =
                  basket.lines.get(apple).map(_.discountedQuantity).getOrElse(0)
                discountsCount should be(1)
              }
              "and total of " in {
                basket.total should be(
                  appleProductPrice.price * 2 + orangeProductPrice.price
                )
              }
            }
          }
          "and empty basket" - {
            val products = List()
            "Then should return £0" in {
              val basket = checkout.calculate(products)
              basket.total should be(Pound(0))
            }
          }
          "and basket with not supported items" - {
            val products = List(Product("UnknownItem"))
            "should return £0" in {
              val basket = checkout.calculate(products)
              basket.total should be(Pound(0))
            }
          }

          "and basket with 2 Apples" - {
            val products = List(Product("Apple"), Product("Apple"))
            "When calculate the basket" - {
              val basket = checkout.calculate(products)
              "Then 1 Apple discount" in {
                val appleDiscount =
                  basket.lines.get(apple).map(_.discountedQuantity).getOrElse(0)
                appleDiscount should be(1)
              }
              "and total of " in {
                basket.total should be(appleProductPrice.price * 1)
              }
            }
          }
          "and basket with 2 Bananas" - {
            val products = List(Product("Banana"), Product("Banana"))
            "When calculate the basket" - {
              val basket = checkout.calculate(products)
              "Then 1 Banana discount" in {
                val bananaDiscount =
                  basket.lines
                    .get(banana)
                    .map(_.discountedQuantity)
                    .getOrElse(0)
                bananaDiscount should be(1)
              }
              "and total of " in {
                basket.total should be(bananaProductPrice.price * 1)
              }
            }
          }
          "and basket with 2 Bananas and 1 Apple" - {
            val products =
              List(Product("Banana"), Product("Banana"), Product("Apple"))
            "When calculate the basket" - {
              val basket = checkout.calculate(products)
              "Then 1 Banana discount as Bundle" in {
                val bananaDiscount =
                  basket.lines
                    .get(banana)
                    .map(_.discountedQuantity)
                    .getOrElse(0)
                bananaDiscount should be(1)
              }
              "and total of " in {
                basket.total should be(
                  bananaProductPrice.price * 1 + appleProductPrice.price * 1
                )
              }
            }
          }
          "and basket with 2 Bananas and 2 Apple" - {
            val products = List(
              Product("Banana"),
              Product("Banana"),
              Product("Apple"),
              Product("Apple")
            )
            "When calculate the basket" - {
              val basket = checkout.calculate(products)
              "Then 1 Banana discounted" in {
                val bananaDiscount =
                  basket.lines
                    .get(banana)
                    .map(_.discountedQuantity)
                    .getOrElse(0)
                bananaDiscount should be(2)
              }
              "Then 1 Apple discounted" in {
                val appleDiscount =
                  basket.lines.get(apple).map(_.discountedQuantity).getOrElse(0)
                appleDiscount should be(1)
              }
              "and total of " in {
                basket.total should be(
                  bananaProductPrice.price * 0 + appleProductPrice.price * 1
                )
              }
            }
          }
          "and basket with 2 Bananas and 4 Apple" - {
            val products = List(
              Product("Banana"),
              Product("Banana"),
              Product("Apple"),
              Product("Apple"),
              Product("Apple"),
              Product("Apple")
            )
            "When calculate the basket" - {
              val basket = checkout.calculate(products)
              "Then 2 Banana discounted" in {
                val bananaDiscount =
                  basket.lines
                    .get(banana)
                    .map(_.discountedQuantity)
                    .getOrElse(0)
                bananaDiscount should be(2)
              }
              "Then 2 Apple discounted" in {
                val appleDiscount =
                  basket.lines.get(apple).map(_.discountedQuantity).getOrElse(0)
                appleDiscount should be(2)
              }
              "and total of " in {
                basket.total should be(
                  bananaProductPrice.price * 0 + appleProductPrice.price * 2
                )
              }
            }
          }
        }
      }
    }
  }
}
