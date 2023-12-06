package example.j5ik2o.oop.domain.refactor2

import example.j5ik2o.common.domain.OrderId
import example.j5ik2o.oop.domain.{Item, Money, OrderItems, Price}

final case class Order(id: OrderId, orderItems: OrderItems) {

  private val calculator = PriceCalculator()

  def totalPrice: Price = calculator.calculate(this)

  def calculateTotalPrice(adjustPrice: Item => Price = _.price): Price = orderItems.calculateTotalPrice(adjustPrice)

}
