package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CheckOutTest extends AnyFreeSpec with Matchers {
  "A CheckOut calculateTotal" - {
    "and a basket of Apple, Apple, Orange, Apple" - {
      val basket = List(Product("Apple"), Product("Apple"), Product("Orange"), Product("Apple"))
      "should return £1.45" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(1.45))
      }
    }
    "and empty basket" - {
      val basket = List()
      "should return £0" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0))
      }
    }
    "and basket with not supported items" - {
      val basket = List(Product("UnknownItem"))
      "should return £0" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0))
      }
    }
    "and basket with Apple, Apple" - {
      val basket = List(Product("Apple"), Product("Apple"))
      "should return £0.6" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.6))
      }
    }
  }


}
