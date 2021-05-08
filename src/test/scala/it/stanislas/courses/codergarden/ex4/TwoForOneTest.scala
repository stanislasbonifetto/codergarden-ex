package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TwoForOneTest extends AnyFreeSpec with Matchers {
  "A TwoForOne with price x" - {
    val price = Pound(3.99)
    val promo = TwoForOne(price)
    "quantity 0" - {
      "discount should be 0" in {
        val discount = promo.calculateDiscount(0)
        discount should be(Pound(0))
      }
    }
    "quantity 1" - {
      "discount should be 0" in {
        val discount = promo.calculateDiscount(1)
        discount should be(Pound(0))
      }
    }
    "quantity 2" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(2)
        discount should be (price)
      }
    }
    "quantity 3" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(3)
        discount should be (price)
      }
    }
    "quantity 4" - {
      "discount should be 2 * x" in {
        val discount = promo.calculateDiscount(4)
        discount should be (2 * price)
      }
    }
  }
}
