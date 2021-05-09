package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CheckOutTest extends AnyFreeSpec with Matchers {
  "A CheckOut calculateTotal" - {
    "and a basket of Apple, Apple, Orange, Apple" - {
      val basket = List("Apple", "Apple", "Orange", "Apple")
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
      val basket = List("UnknownItem")
      "should return £0" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0))
      }
    }
    "and basket with Apple, Apple" - {
      val basket = List("Apple", "Apple")
      "should return £0.6" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.6))
      }
    }
    "and basket with Banana" - {
      val basket = List("Banana")
      "should return £0.2" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.2))
      }
    }
    "and basket with Banana, Banana" - {
      val basket = List("Banana", "Banana")
      "should return £0.2" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.2))
      }
    }
    "and basket with Banana, Banana, Apple" - {
      val basket = List("Banana", "Banana", "Apple")
      "should return £0.8" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.8))
      }
    }
    "and basket with Banana, Banana, Banana, Apple" - {
      val basket = List("Banana", "Banana", "Banana", "Apple")
      "should return £0.8" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.8))
      }
    }
    "and basket with Banana, Banana, Apple, Apple" - {
      val basket = List("Banana", "Banana", "Apple", "Apple")
      "should return £0.6" in {
        val total = Checkout.calculateTotal(basket)
        total should be (Pound(0.6))
      }
    }
  }
}
