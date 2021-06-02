package it.stanislas.courses.codergarden.ex4

import scala.collection.immutable.Map

sealed trait Promotion {
  def applyDiscount(basket: Basket): Basket
}

abstract class PromotionImpl extends Promotion {
  def applyDiscount(basket: Basket): Basket = {
    basket match {
      case Basket(lines) => Basket(applyDiscounts(lines))
    }
  }

  def applyDiscounts(lines: Map[Product, Line]): Map[Product, Line]

  protected def updateLine(
      line: Line,
      lines: Map[Product, Line]
  ): Map[Product, Line] = {
    if (hasValidTotalAndQuantityDiscount(line))
      lines + (line.productPrice.product -> line)
    else
      lines
  }

  private def hasValidTotalAndQuantityDiscount(line: Line): Boolean =
    line.total.value >= 0 && line.quantity >= line.discountedQuantity

  def addDiscount(discount: Discount, line: Line): Option[Line] = {
    val newLine = line.copy(discounts =
      discount :: line.discounts
    )
    Some(newLine).filter(hasValidTotalAndQuantityDiscount)
  }
}

abstract class ProductQuantityPromotion(product: Product)
    extends PromotionImpl {
  val discountQuantity: Int
  val freeQuantity: Int

  def applyDiscounts(lines: Map[Product, Line]): Map[Product, Line] = {
    lines
      .get(product)
      .map(line => {
        val maybeDiscount = calculateDiscount(
          line.productPrice.price,
          line.remainQuantity
        )
        maybeDiscount
          .flatMap(discount => addDiscount(discount, line))
          .map(line => lines + (line.productPrice.product -> line))
          .getOrElse(lines)
      })
      .getOrElse(lines)
  }

  def calculateDiscount(
      productPrice: Money,
      quantity: Int
  ): Option[Discount] = {
    val discountedQuantity = (quantity / discountQuantity) * freeQuantity
    if (discountedQuantity % 1 == 0)
      Some(
        Discount(this, productPrice * discountedQuantity, discountedQuantity)
      )
    else
      None
  }
}

case class TwoForOne(product: Product)
    extends ProductQuantityPromotion(product) {
  override val discountQuantity: Int = 2
  override val freeQuantity: Int = 1
}
case class TreeForTwo(product: Product)
    extends ProductQuantityPromotion(product) {
  override val discountQuantity: Int = 3
  override val freeQuantity: Int = 1
}

case class ProductBundlePromotion(bundle: (Product, Product))
    extends PromotionImpl {
  def applyDiscounts(lines: Map[Product, Line]): Map[Product, Line] = {

    val maybeCheapestAndExpenseBundles = getCheapestAndExpenseBundles(lines)

    maybeCheapestAndExpenseBundles
      .map {
        case (cheapest, expensive) =>
          val quantityToDiscount =
            cheapest.remainQuantity - expensive.remainQuantity
          val newLine = cheapest.copy(discounts =
            Discount(
              this,
              cheapest.productPrice.price * quantityToDiscount,
              quantityToDiscount
            ) :: cheapest.discounts
          )
          if (
            newLine.total.value >= 0 && newLine.quantity >= newLine.discountedQuantity
          )
            lines + (cheapest.productPrice.product -> newLine)
          else
            lines
      }
      .getOrElse(lines)
  }

  private def getCheapestAndExpenseBundles(
      lines: Map[Product, Line]
  ): Option[(Line, Line)] = {
    val bundlesLinesSortedByPrice =
      List(lines.get(bundle._1), lines.get(bundle._2)).flatten
        .sortBy(_.productPrice.price.value)

    bundlesLinesSortedByPrice match {
      case c :: e :: Nil => Some((c, e))
      case _             => None
    }
  }
}

case class PercentageDiscount(percentage: Double) extends PromotionImpl {
  def applyDiscounts(lines: Map[Product, Line]): Map[Product, Line] = {
    lines.map {
      case (product, line) => {
        val newLine = line.copy(discounts =
          Discount(
            this,
            line.productPrice.price * percentage
          ) :: line.discounts
        )
        if (newLine.total.value >= 0)
          product -> newLine
        else
          product -> line
      }
    }
  }
}

object NoPromo extends Promotion {
  override def applyDiscount(basket: Basket): Basket = basket
}

case class Discount(
    promotion: Promotion,
    value: Money,
    discountedQuantity: Int = 0
)
