package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ProductBundleTest extends AnyFreeSpec with Matchers {
  "Given A ProductBundle" - {
    val cheapest = Product("cheapest")
    val cheapestProductPrice = ProductPrice(cheapest, Pound(3.99))
    val expensive = Product("expensive")
    val expensiveProductPrice = ProductPrice(expensive, Pound(4.99))
    val promo = ProductBundlePromotion(cheapest, expensive)
    "and lines with cheapest  with qty 1 and expensive qty 1" - {
      val lines = Map(
        cheapest -> Line(cheapestProductPrice, 1),
        expensive -> Line(expensiveProductPrice, 1)
      )
      "When apply discounts" - {
        val discountedLines = promo.applyDiscounts(lines)
        "Then" - {
          "cheapest should be free" in {
            val cheapestLine = discountedLines(cheapest)
            cheapestLine.discountedQuantity should be(1)
            cheapestLine.total should be(Pound(0))
          }
        }
      }
    }
    "and lines with cheapest with qty 2 and expensive qty 1" - {
      val lines = Map(
        cheapest -> Line(cheapestProductPrice, 2),
        expensive -> Line(expensiveProductPrice, 1)
      )
      "When apply discounts" - {
        val discountedLines = promo.applyDiscounts(lines)
        "Then" - {
          "1 cheapest should be free" in {
            val cheapestLine = discountedLines(cheapest)
            cheapestLine.discountedQuantity should be(1)
          }
        }
      }
    }
    "and lines with cheapest with qty 1 and expensive qty 2" - {
      val lines = Map(
        cheapest -> Line(cheapestProductPrice, 1),
        expensive -> Line(expensiveProductPrice, 2)
      )
      "When apply discounts" - {
        val discountedLines = promo.applyDiscounts(lines)
        "Then" - {
          "1 cheapest should be free" in {
            val cheapestLine = discountedLines(cheapest)
            cheapestLine.discountedQuantity should be(1)
          }
        }
      }
    }
    "and lines with cheapest with qty 1 and already discounted and expensive qty 1" - {
      val lines = Map(
        cheapest -> Line(
          cheapestProductPrice,
          1,
          List(Discount(NoPromo, cheapestProductPrice.price, 1))
        ),
        expensive -> Line(expensiveProductPrice, 2)
      )
      "When apply discounts" - {
        val discountedLines = promo.applyDiscounts(lines)
        "Then" - {
          "the bundle should not applied" in {
            val cheapestLine = discountedLines(cheapest)
            val cheapestBundleDiscount =
              cheapestLine.discounts.filter(_.promotion == promo)
            cheapestBundleDiscount should be(List())
          }
        }
      }
    }
  }
}
