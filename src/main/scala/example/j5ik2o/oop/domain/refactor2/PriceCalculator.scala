package example.j5ik2o.oop.domain.refactor2

import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.oop.domain.Money

import scala.language.implicitConversions

trait PriceCalculator {
  def calculate(order: Order): Money
}

object PriceCalculator {
  def apply(): PriceCalculator = {
    new TaxCalculator(new ShippingCalculator(new DiscountCalculator(new DefaultCalculator)))
  }
}

class DefaultCalculator extends PriceCalculator {
  override def calculate(order: Order): Money = {
    order.calculateTotalPrice()
  }
}

private abstract class PriceCalculatorDecorator(underlying: PriceCalculator) extends PriceCalculator {
  override def calculate(order: Order): Money = {
    underlying.calculate(order)
  }
}

class DiscountCalculator(priceCalculator: PriceCalculator) extends PriceCalculatorDecorator(priceCalculator) {
  override def calculate(order: Order): Money = {
    val price = super.calculate(order)
    price - order.calculateTotalPrice { item =>
      item.price match {
        case amount if amount >= Money(10000) => item.price * 0.1
        case amount if amount >= Money(5000) => item.price * 0.05
        case _ => Money.zero()
      }
    }
  }
}

class ShippingCalculator(priceCalculator: PriceCalculator) extends PriceCalculatorDecorator(priceCalculator) {
  override def calculate(order: Order): Money = {
    val price = super.calculate(order)
    price + order.calculateTotalPrice { item =>
      item.itemType match {
        case ItemType.Download => Money.zero()
        case ItemType.Car => Money(50000)
        case _                 => item.price * 0.1
      }
    }
  }
}

class TaxCalculator(priceCalculator: PriceCalculator) extends PriceCalculatorDecorator(priceCalculator) {
  override def calculate(order: Order): Money = {
    val price = super.calculate(order)
    price + order.calculateTotalPrice { item =>
      item.itemType match {
        case ItemType.Car => item.price * 0.2
        case _            => item.price * 0.1
      }
    }
  }
}
