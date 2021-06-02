package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TwoForOneTest extends AnyFreeSpec with Matchers {
  "A TwoForOne with price x" - {
    val price = Pound(3.99)
    val product = Product("A")
    val promo = TwoForOne(product)
    "quantity 0" - {
      "should no discount" in {
        val maybeDiscount = promo.calculateDiscount(price, 0)
        maybeDiscount should be(None)
      }
    }
    "quantity 1" - {
      "should no discount" in {
        val maybeDiscount = promo.calculateDiscount(price, 1)
        maybeDiscount should be(None)
      }
    }
    "quantity 2" - {
      "discount should be x" in {
        val maybeDiscount = promo.calculateDiscount(price, 2)
        maybeDiscount should be(Some(Discount(promo, price, 1)))
      }
    }
    "quantity 3" - {
      "discount should be x" in {
        val maybeDiscount = promo.calculateDiscount(price, 3)
        maybeDiscount should be(Some(Discount(promo, price, 1)))
      }
    }
    "quantity 4" - {
      "discount should be 2 * x" in {
        val maybeDiscount = promo.calculateDiscount(price, 4)
        maybeDiscount should be(
          Some(Discount(promo, price * 2, 2))
        )
      }
    }
  }
}
