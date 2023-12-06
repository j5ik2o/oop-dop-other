package example.j5ik2o.dop.domain.refactor2

import example.j5ik2o.common
import example.j5ik2o.common.domain
import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.dop.domain.{Item, Money, Price}

object PriceCalculator {

  def adjustPrice(item: Item): Price =
    calculateShippingCost(calculateDiscount(calculateTax(calculateBasePrice)))(item)

  private def calculateBasePrice(item: Item): Price = item.price

  private def calculateShippingCost(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    Price.plus(
      basePrice,
      item.itemType match {
        case ItemType.Download => Price.zero()
        case ItemType.Car      => Price(Money(50000))
        case _                 => Price.times(item.price, 0.1)
      }
    )
  }

  private def calculateDiscount(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    Price.minus(
      basePrice,
      item.price.value.amount match {
        case amount if amount >= 10000 => Price.times(item.price, 0.1)
        case amount if amount >= 5000  => Price.times(item.price, 0.05)
        case _                         => Price.zero()
      }
    )
  }

  private def calculateTax(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    Price.plus(
      basePrice,
      item.itemType match {
        case domain.ItemType.Car => Price.times(item.price, 0.2)
        case _                           => Price.times(item.price, 0.1)
      }
    )
  }
}
