package it.stanislas.courses.codergarden.ex4

case class Basket(lines: Map[Product, Line]) {
  val totalPrice: Money = lines.values
    .map(_.totalPrice)
    .foldLeft(Pound(0): Money)((s, d) => s + d)

  val saving: Money = lines.values
    .map(_.saving)
    .foldLeft(Pound(0): Money)((s, d) => s + d)

  val total: Money = totalPrice - saving
}

case class Line(
    productPrice: ProductPrice,
    quantity: Int,
    discounts: List[Discount] = List()
) {
  val totalPrice: Money = productPrice.price * quantity
  val discountedQuantity: Int = discounts.map(_.discountedQuantity).sum
  val remainQuantity: Int = quantity - discountedQuantity
  val saving: Money = discounts.foldLeft(Pound(0): Money)((s, d) => s + d.value)
  val total: Money = totalPrice - saving
}
