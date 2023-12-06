package example.j5ik2o.oop.domain.refactor1

import example.j5ik2o.common.domain.OrderId
import example.j5ik2o.oop.domain.{OrderItems, Price}

final case class Order(id: OrderId, orderItems: OrderItems) {

  def totalPrice: Price = orderItems.calculateTotalPrice(PriceCalculator.adjustPrice)

}
