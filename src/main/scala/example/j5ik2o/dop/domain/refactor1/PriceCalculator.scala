package example.j5ik2o.dop.domain.refactor1

import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.dop.domain.{Item, Money, Price}

object PriceCalculator {
  def adjustPrice(item: Item): Price = {
    val basePrice    = calculateBasePrice(item)
    val shippingCost = calculateShippingCost(item)
    val discount     = calculateDiscount(item)
    val tax          = calculateTax(item)
    Price.plus(Price.minus(Price.plus(basePrice, shippingCost), discount), tax)
  }

  private def calculateBasePrice(item: Item): Price = item.price

  private def calculateShippingCost(item: Item): Price = item.itemType match {
    case ItemType.Download => Price.zero()
    case ItemType.Car      => Price(Money(50000))
    case _                 => Price.times(item.price, 0.1)
  }

  private def calculateDiscount(item: Item): Price = item.price.value.amount match {
    case amount if amount >= 10000 => Price.times(item.price, 0.1)
    case amount if amount >= 5000  => Price.times(item.price, 0.05)
    case _                         => Price.zero()
  }

  private def calculateTax(item: Item): Price = item.itemType match {
    case ItemType.Car => Price.times(item.price, 0.2)
    case _            => Price.times(item.price, 0.1)
  }
}
