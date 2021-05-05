package it.stanislas.courses.codergarden.ex4

case class Pound(value: BigDecimal) extends AnyVal {
  def + (that: Pound): Pound = Pound(this.value + that.value)
  override def toString: String = s"£${super.toString}"
}