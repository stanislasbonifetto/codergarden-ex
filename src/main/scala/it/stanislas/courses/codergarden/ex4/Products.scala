package it.stanislas.courses.codergarden.ex4

case class Product(name: String)
object Apple extends Product("Apple")
object Orange extends Product("Orange")

case class ProductData(productPrice: ProductPrice, promotion: Promotion = NoPromo)

case class ProductPrice(product: Product, price: Money = Pound(0))