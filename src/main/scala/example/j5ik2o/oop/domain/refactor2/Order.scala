package example.j5ik2o.oop.domain.refactor2

import example.j5ik2o.oop.domain.{ Item, Money, OrderId, OrderItems }

final case class Order(id: OrderId, orderItems: OrderItems) {

  private val calculator = PriceCalculator()

  def totalPrice: Money = calculator.calculate(this)

  def calculateTotalPrice(adjustPrice: Item => Money = _.price): Money = orderItems.calculateTotalPrice(adjustPrice)

}
