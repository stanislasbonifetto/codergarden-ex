package it.stanislas.courses.codergarden.ex4

case class Product(name: String)
object Apple extends Product("Apple")
object Orange extends Product("Orange")
object Banana extends Product("Banana")
object NoProduct extends Product("NoProduct")

case class ProductPrice(product: Product, price: Money = Pound(0))
