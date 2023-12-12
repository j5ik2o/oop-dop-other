package example.j5ik2o.dop.domain

import org.scalatest.freespec.AnyFreeSpec

class MoneySpec extends AnyFreeSpec {
  "Money" - {
    "negated" in {
      val money  = Money(100)
      val result = -money
      assert(result == Money(-100))
    }
    "add" in {
      val money1 = Money(100)
      val money2 = Money(200)
      val result = money1 + money2
      assert(result == Money(300))
      val result2 = Money.+(money1)(money2)
      assert(result2 == Money(300))
    }
    "subtract" in {
      val money1 = Money(100)
      val money2 = Money(200)
      val result = money1 - money2
      assert(result == Money(-100))
    }
    "multiply" in {
      val money1 = Money(100)
      val result = money1 * 2
      assert(result == Money(200))
    }
    "divide" in {
      val money1 = Money(100)
      val result = money1 / 2
      assert(result == Money(50))
    }
  }
}
