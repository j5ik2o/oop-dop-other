package example.j5ik2o.dop.domain

import java.util.{Currency, Locale}

// 原則2, 3
opaque type Money = Map[String, Any]

object Money {
  final val DefaultCurrency: Currency = Currency.getInstance(Locale.getDefault)
  final val JPY: Currency = Currency.getInstance("JPY")

  def zero(currency: Currency = DefaultCurrency): Money = Money(0, currency)

  def apply(amount: BigDecimal, currency: Currency = DefaultCurrency): Money =
    Map("amount" -> amount, "currency" -> currency)

  def apply(amount: BigDecimal, currency: String): Money =
    Money(amount, Currency.getInstance(currency))

  def unapply(self: Money): Option[(BigDecimal, Currency)] =
    Some((self.amount, self.currency))

  // 原則1, 3
  extension (self: Money) {
    // 原則4
    def amount: BigDecimal = self("amount").asInstanceOf[BigDecimal]
    def currency: Currency = self("currency").asInstanceOf[Currency]

    // 二項演算子を定義。 例) val m3: Money = m1 + m2
    infix def +(other: Money): Money = {
      require(currency == other.currency)
      Money(amount + other.amount, currency)
    }
    infix def -(other: Money): Money = self + -other
    infix def *(multiplier: Int): Money = Money(amount * multiplier, currency)
    infix def *(multiplier: Double): Money = Money(amount * multiplier, currency)
    infix def /(divisor: Int): Money = Money(amount / divisor, currency)
    infix def /(divisor: Double): Money = Money(amount / divisor, currency)

    // 単項演算子を定義。 例) val m2: Money = -m1
    def unary_- : Money = Money(-amount, currency)
    
    def negated: Money = -self
    def plus(other: Money): Money = self + other
    def minus(other: Money): Money = self - other
    def times(multiplier: Int): Money = self * multiplier
    def times(multiplier: Double): Money = self * multiplier
    def divide(divisor: Int): Money = self / divisor
    def divide(divisor: Double): Money = self / divisor
  }

  // Moneyどうしの比較
  given Ordering[Money] = (x: Money, y: Money) => {
    require(x.currency == y.currency)
    x.amount.compare(y.amount)
  }

  // IntからMoneyへの暗黙的型変換。 例) val m1: Money = 100
  given Conversion[Int, Money] = (amount: Int) => Money(amount, DefaultCurrency)

}
