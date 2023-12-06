package example.j5ik2o.dop.domain.refactor1

import example.j5ik2o.common
import example.j5ik2o.common.domain.OrderId
import example.j5ik2o.dop.domain.{OrderItems, Price}

final case class Order(id: OrderId, orderItems: OrderItems)

object Order {

  def totalPrice(order: Order): Price = OrderItems.calculateTotalPrice(order.orderItems, PriceCalculator.adjustPrice)

}
