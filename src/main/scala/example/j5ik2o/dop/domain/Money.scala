package example.j5ik2o.dop.domain

import java.util.{Currency, Locale}

opaque type Money = Map[String, Any]

object Money {
  val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  val JPY: Currency             = Currency.getInstance("JPY")

  given ordering: Ordering[Money] = (x: Money, y: Money) => {
    require(currency(x) == currency(y))
    amount(x).compare(amount(y))
  }

  given int2Money: Conversion[Int, Money] = (amount: Int) => Money(amount, DefaultCurrency)

  def zero(currency: Currency = DefaultCurrency): Money = Money(0, currency)

  def plus(self: Money, other: Money): Money = {
    require(currency(self) == currency(other))
    Money(amount(self) + amount(other), currency(self))
  }

  def apply(amount: BigDecimal, currency: Currency = DefaultCurrency): Money = {
    require(amount >= 0)
    Map("amount" -> amount, "currency" -> currency)
  }

  def apply(amount: BigDecimal, currency: String): Money = {
    Money(amount, Currency.getInstance(currency))
  }

  def amount(self: Money): BigDecimal = self("amount").asInstanceOf[BigDecimal]

  def currency(self: Money): Currency = self("currency").asInstanceOf[Currency]

  def minus(self: Money, other: Money): Money = {
    require(currency(self) == currency(other))
    Money(amount(self) - amount(other), currency(self))
  }

  def times(self: Money, multiplier: Int): Money = Money(amount(self) * multiplier, currency(self))

  def times(self: Money, multiplier: Double): Money = Money(amount(self) * multiplier, currency(self))

  def toBigDecimal(self: Money): BigDecimal = amount(self)
}
