package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TreeForTwoTest extends AnyFreeSpec with Matchers {
  "A TreeForTwo with price x" - {
    val price = Pound(3.99)
    val productPrice = ProductPrice(Product("A"), price)
    val promo = TreeForTwo(productPrice)
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
      "discount should be 0" in {
        val discount = promo.calculateDiscount(2)
        discount should be (Pound(0))
      }
    }
    "quantity 3" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(3)
        discount should be (price)
      }
    }
    "quantity 4" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(4)
        discount should be (price)
      }
    }
    "quantity 6" - {
      "discount should be 2 * x" in {
        val discount = promo.calculateDiscount(6)
        discount should be (price * 2)
      }
    }
  }
}
