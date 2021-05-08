package it.stanislas.courses.codergarden.ex4

case class Pound(value: BigDecimal) extends AnyVal {
  def + (that: Pound): Pound = Pound(this.value + that.value)
  def - (that: Pound): Pound = Pound(this.value - that.value)
  def * (that: Pound): Pound = Pound(this.value * that.value)
  def / (that: Pound): Pound = Pound(this.value / that.value)
  override def toString: String = s"£${super.toString}"
}

object Pound {
  implicit def IntToPound(x: Int): Pound = Pound(x)
  implicit def DoubleToPound(x: Double): Pound = Pound(x)
}