package example.j5ik2o.oop.domain.refactor3

import example.j5ik2o.oop.domain.{Money, OrderId, OrderItems}

final case class Order(id: OrderId, orderItems: OrderItems) {

  def totalPrice: Money = orderItems.calculateTotalPrice(PriceCalculator.adjustPrice)

}
