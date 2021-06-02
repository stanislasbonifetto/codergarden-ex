package it.stanislas.courses.codergarden.ex4

class BasketEngine(val promotions: List[Promotion]) {
  def calculate(basket: Basket): Basket =
    promotions
      .foldLeft(basket)((b: Basket, p: Promotion) => p.applyDiscount(b))
}
