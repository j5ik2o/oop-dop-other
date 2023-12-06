package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.Quantity
import example.j5ik2o.dop.domain.{ Money, Price }

import java.util.Currency
import scala.annotation.targetName
import scala.language.implicitConversions

final case class Price(value: Money)

object Price {
  def zero(currency: Currency = Money.DefaultCurrency): Price = Price(Money.zero(currency))

  given Ordering[Price] = (x: Price, y: Price) => {
    Money.given_Ordering_Money.compare(x.value, y.value)
  }

  given int2Price: Conversion[Int, Price] = (amount: Int) => Price(amount)

  def plus(self: Price, other: Price): Price = Price(Money.plus(self.value, other.value))

  def minus(self: Price, other: Price): Price = Price(Money.minus(self.value, other.value))

  def times(self: Price, multiplier: Quantity): Price = Price(Money.times(self.value, multiplier.value))

  def times(self: Price, multiplier: Double): Price = Price(Money.times(self.value, multiplier))

  def currency(self: Price): Currency = self.value.currency

}
