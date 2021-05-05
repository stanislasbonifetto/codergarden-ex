package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CheckOutTest extends AnyFreeSpec with Matchers {
  "A CheckOut calculateTotal" - {
    "and a basket of Apple, Apple, Orange, Apple" - {
      val basket = List("Apple", "Apple", "Orange", "Apple")
      "should return £2.05" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(2.05))
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
      val basket = List("UnknownItem")
      "should return £0" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0))
      }
    }
  }


}
