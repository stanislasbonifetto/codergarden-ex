package it.stanislas.courses.codergarden.ex4

import scala.language.implicitConversions

trait Money {
  val value: BigDecimal
  val symbol: String
  def +(that: Money): Money
  def -(that: Money): Money
  def *(that: Money): Money
  def *(that: Int): Money
  def /(that: Money): Money
  override def toString: String = s"$symbol$value"
}

case class Pound(value: BigDecimal) extends Money {
  override val symbol: String = "£"
  def +(that: Money): Money = Pound(this.value + that.value)
  def -(that: Money): Money = Pound(this.value - that.value)
  def *(that: Money): Money = Pound(this.value * that.value)
  def *(that: Int): Money = Pound(this.value * that)
  def /(that: Money): Money = Pound(this.value / that.value)
}
