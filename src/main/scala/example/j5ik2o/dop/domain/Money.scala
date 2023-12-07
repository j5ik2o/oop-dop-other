package example.j5ik2o.dop.domain

import java.util.{Currency, Locale}
import scala.annotation.targetName

opaque type Money = Map[String, Any]

object Money {
  val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  val JPY: Currency             = Currency.getInstance("JPY")

  given ordering: Ordering[Money] = (x: Money, y: Money) => {
    require(currency(x) == currency(y))
    amount(x).compare(amount(y))
  }

  given int2Money: Conversion[Int, Money] = (amount: Int) => Money(amount, DefaultCurrency)

  def apply(amount: BigDecimal, currency: Currency = DefaultCurrency): Money = {
    require(amount >= 0)
    Map("amount" -> amount, "currency" -> currency)
  }

  def zero(currency: Currency = DefaultCurrency): Money = Money(0, currency)

  def apply(amount: BigDecimal, currency: String): Money =
    Money(amount, Currency.getInstance(currency))

  def unapply(self: Money): Option[(BigDecimal, Currency)] =
    Some((self.amount, self.currency))

  extension (self: Money) {
    def amount: BigDecimal = self("amount").asInstanceOf[BigDecimal]
    def currency: Currency = self("currency").asInstanceOf[Currency]
    @targetName("plus")
    infix def +(other: Money): Money = {
      require(currency == other.currency)
      Money(amount + other.amount, currency)
    }
    @targetName("minus")
    infix def -(other: Money): Money = {
      require(currency == other.currency)
      Money(amount - other.amount, currency)
    }
    @targetName("times")
    infix def *(multiplier: Int): Money = Money(amount * multiplier, currency)
    @targetName("times")
    infix def *(multiplier: Double): Money = Money(amount * multiplier, currency)
  }

}
