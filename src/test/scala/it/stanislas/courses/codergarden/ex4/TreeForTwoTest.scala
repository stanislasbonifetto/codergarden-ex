package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TreeForTwoTest extends AnyFreeSpec with Matchers {
  "A TreeForTwo with price x" - {
    val price = Pound(3.99)
    val product = Product("A")
    val promo = TreeForTwo(product)
    "quantity 0" - {
      "should no discount" in {
        val discount = promo.calculateDiscount(price, 0)
        discount should be(None)
      }
    }
    "quantity 1" - {
      "should no discount" in {
        val discount = promo.calculateDiscount(price, 0)
        discount should be(None)
      }
    }
    "quantity 2" - {
      "should no discount" in {
        val discount = promo.calculateDiscount(price, 2)
        discount should be(None)
      }
    }
    "quantity 3" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(price, 3)
        discount should be(Some(Discount(promo, price, 1)))
      }
    }
    "quantity 4" - {
      "discount should be x" in {
        val discount = promo.calculateDiscount(price, 4)
        discount should be(Some(Discount(promo, price, 1)))
      }
    }
    "quantity 6" - {
      "discount should be 2 * x" in {
        val discount = promo.calculateDiscount(price, 6)
        discount should be(Some(Discount(promo, price * 2, 2)))
      }
    }
  }
}
