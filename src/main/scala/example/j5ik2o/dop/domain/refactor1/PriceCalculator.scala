package example.j5ik2o.dop.domain.refactor1

import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.dop.domain.{Item, Money}

object PriceCalculator {
  def adjustPrice(item: Item): Money = {
    val basePrice    = calculateBasePrice(item)
    val shippingCost = calculateShippingCost(item)
    val discount     = calculateDiscount(item)
    val tax          = calculateTax(item)
    basePrice + shippingCost - discount + tax
  }

  private def calculateBasePrice(item: Item): Money = Item.price(item)

  private def calculateShippingCost(item: Item): Money = Item.itemType(item) match {
    case ItemType.Download => Money.zero()
    case ItemType.Car => Money(50000)
    case _ => Item.price(item) * 0.1
  }

  private def calculateDiscount(item: Item): Money = Money.amount(Item.price(item)) match {
    case amount if amount >= 10000 => Item.price(item) * 0.1
    case amount if amount >= 5000 => Item.price(item) * 0.05
    case _ => Money.zero()
  }

  private def calculateTax(item: Item): Money = Item.itemType(item) match {
    case ItemType.Car => Item.price(item) * 0.2
    case _ => Item.price(item) * 0.1
  }
}
