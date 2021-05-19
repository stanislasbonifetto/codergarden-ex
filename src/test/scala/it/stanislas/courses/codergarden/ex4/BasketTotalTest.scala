package it.stanislas.courses.codergarden.ex4

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class BasketTotalTest
    extends AnyFreeSpec
    with Matchers
    with ScalaCheckPropertyChecks {
  "A BasketTotal" - {
    val basketTotal = BasketTotal()
    "should be total, discount, discountedTotal = 0" in {
      basketTotal.total should be(Pound(0))
      basketTotal.discount should be(Pound(0))
      basketTotal.discountedTotal should be(Pound(0))
    }
  }
  "A BasketTotal" - {
    "add a  two products data of x and y pound should be total, discountedTotal = x + y and discount = 0" in {
      forAll { (x: BigDecimal, y: BigDecimal) =>
        val priceX = Pound(x)
        val priceY = Pound(y)
        val basketTotal = BasketTotal().addProduct(
          ProductData(ProductPrice(Product("randomProductX"), priceX))
        )
        val productData =
          ProductData(ProductPrice(Product("randomProductY"), priceY))
        val basketTotalAddedProduct = basketTotal.addProduct(productData)
        val expectedTotal = priceX + priceY
        basketTotalAddedProduct.total should be(expectedTotal)
        basketTotalAddedProduct.discount should be(Pound(0))
        basketTotalAddedProduct.discountedTotal should be(expectedTotal)
      }
    }
  }
}
