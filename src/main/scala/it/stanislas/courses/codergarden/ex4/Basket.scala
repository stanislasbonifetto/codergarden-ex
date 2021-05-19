package it.stanislas.courses.codergarden.ex4

case class Basket(lines: List[Line])

case class Line(productPrice: ProductPrice, quantity: Int) {
}