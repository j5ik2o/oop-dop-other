package example.j5ik2o.script.transaction.model

import java.util.{Currency, Locale}
import scala.annotation.targetName

opaque type Money = (BigDecimal, Currency)

object Money {
  val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  val JPY: Currency = Currency.getInstance("JPY")

  def zero(currency: Currency = DefaultCurrency): Money = Money(BigDecimal(0), currency)

  def apply(amount: BigDecimal, currency: Currency): Money = (amount, currency)

  def unapply(money: Money): Option[(BigDecimal, Currency)] = Some(money)

  extension (money: Money) {
    def amount: BigDecimal = money._1
    def currency: Currency = money._2
    def toBigDecimal: BigDecimal = amount
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
    def *(multiplier: Double): Money = Money(amount * multiplier, currency)
    @targetName("times")
    def *(multiplier: Int): Money = Money(amount * multiplier, currency)
  }

}
