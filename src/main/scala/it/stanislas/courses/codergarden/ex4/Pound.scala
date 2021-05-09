package it.stanislas.courses.codergarden.ex4

case class Pound(value: BigDecimal) extends AnyVal {
  def + (that: Pound): Pound = Pound(this.value + that.value)
  def - (that: Pound): Pound = Pound(this.value - that.value)
  def * (that: Pound): Pound = Pound(this.value * that.value)
  override def toString: String = s"£${value}"
}

object Pound {
  implicit def intToPound(x: Int) = Pound(x)
}