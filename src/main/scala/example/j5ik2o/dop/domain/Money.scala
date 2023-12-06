package example.j5ik2o.dop.domain

import example.j5ik2o.dop.domain.Money

import java.util.{ Currency, Locale }
import scala.annotation.targetName

case class Money(amount: BigDecimal, currency: Currency) // extends Ordered[Money] {
//
//  override def compare(that: Money): Int = {
//    require(currency == that.currency)
//    amount.compare(that.amount)
//  }
//}

object Money {
  val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  val JPY: Currency             = Currency.getInstance("JPY")

  given Ordering[Money] = (x: Money, y: Money) => {
    require(x.currency == y.currency)
    x.amount.compare(y.amount)
  }

  given int2Money: Conversion[Int, Money] = (amount: Int) => Money(amount, DefaultCurrency)

  def apply(amount: BigDecimal, currency: Currency = DefaultCurrency): Money = {
    require(amount >= 0)
    new Money(amount, currency)
  }

  def apply(amount: BigDecimal, currency: String): Money = {
    Money(amount, Currency.getInstance(currency))
  }

  def zero(currency: Currency): Money = Money(0, currency)

  def plus(self: Money, other: Money): Money = {
    require(self.currency == other.currency)
    Money(self.amount + other.amount, self.currency)
  }

  def minus(self: Money, other: Money): Money = {
    require(self.currency == other.currency)
    Money(self.amount - other.amount, self.currency)
  }

  def times(self: Money, multiplier: Int): Money = Money(self.amount * multiplier, self.currency)

  def times(self: Money, multiplier: Double): Money = Money(self.amount * multiplier, self.currency)

  def toBigDecimal(self: Money): BigDecimal = self.amount
}
