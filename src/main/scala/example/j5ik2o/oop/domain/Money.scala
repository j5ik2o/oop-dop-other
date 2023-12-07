package example.j5ik2o.oop.domain

import java.util.{Currency, Locale}
import scala.annotation.targetName

case class Money(amount: BigDecimal, currency: Currency) extends Ordered[Money] {
  @targetName("plus")
  def +(other: Money): Money = {
    require(currency == other.currency)
    Money(amount + other.amount, currency)
  }
  @targetName("minus")
  def -(other: Money): Money = {
    require(currency == other.currency)
    Money(amount - other.amount, currency)
  }
  @targetName("times")
  def *(multiplier: Int): Money = Money(amount * multiplier, currency)
  @targetName("times")
  def *(multiplier: Double): Money = Money(amount * multiplier, currency)
  def toBigDecimal: BigDecimal     = amount

  override def compare(that: Money): Int = {
    require(currency == that.currency)
    amount.compare(that.amount)
  }
}

object Money {
  val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  val JPY: Currency             = Currency.getInstance("JPY")

  given int2Money: Conversion[Int, Money] = (amount: Int) => Money(amount, DefaultCurrency)

  def apply(amount: BigDecimal, currency: String): Money = {
    Money(amount, Currency.getInstance(currency))
  }

  def zero(currency: Currency = DefaultCurrency): Money = Money(0, currency)

  def apply(amount: BigDecimal, currency: Currency = DefaultCurrency): Money = {
    require(amount >= 0)
    new Money(amount, currency)
  }
}
