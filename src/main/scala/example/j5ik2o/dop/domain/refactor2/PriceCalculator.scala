package example.j5ik2o.dop.domain.refactor2

import example.j5ik2o.common
import example.j5ik2o.common.domain
import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.dop.domain.{Item, Money}

object PriceCalculator {

  def adjustPrice(item: Item): Money =
    calculateShippingCost(calculateDiscount(calculateTax(calculateBasePrice)))(item)

  private def calculateBasePrice(item: Item): Money = Item.price(item)

  private def calculateShippingCost(baseCalculation: Item => Money)(item: Item): Money = {
    val basePrice = baseCalculation(item)
    Money.plus(
      basePrice,
      Item.itemType(item) match {
        case ItemType.Download => Money.zero()
        case ItemType.Car => Money(50000)
        case _ => Money.times(Item.price(item), 0.1)
      }
    )
  }

  private def calculateDiscount(baseCalculation: Item => Money)(item: Item): Money = {
    val basePrice = baseCalculation(item)
    Money.minus(
      basePrice,
      Money.amount(Item.price(item)) match {
        case amount if amount >= 10000 => Money.times(Item.price(item), 0.1)
        case amount if amount >= 5000 => Money.times(Item.price(item), 0.05)
        case _ => Money.zero()
      }
    )
  }

  private def calculateTax(baseCalculation: Item => Money)(item: Item): Money = {
    val basePrice = baseCalculation(item)
    Money.plus(
      basePrice,
      Item.itemType(item) match {
        case domain.ItemType.Car => Money.times(Item.price(item), 0.2)
        case _ => Money.times(Item.price(item), 0.1)
      }
    )
  }
}
