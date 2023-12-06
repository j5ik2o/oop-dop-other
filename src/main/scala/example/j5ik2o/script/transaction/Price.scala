package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.Quantity
import example.j5ik2o.oop.domain.Money

import java.util.Currency
import scala.annotation.targetName
import scala.language.implicitConversions

final case class Price(value: Money) extends Ordered[Price] {
  @targetName("plus")
  def +(other: Price): Price = copy(value + other.value)

  @targetName("minus")
  def -(other: Price): Price = copy(value - other.value)

  @targetName("times")
  def *(multiplier: Quantity): Price = copy(value * multiplier.value)

  @targetName("times")
  def *(multiplier: Double): Price = copy(value * multiplier)

  def currency: Currency = value.currency

  override def compare(that: Price): Int = this.value compare that.value
}

object Price {
  def zero(currency: Currency = Money.DefaultCurrency): Price = Price(Money.zero(currency))

  given int2Price: Conversion[Int, Price] = (amount: Int) => Price(amount)

}
