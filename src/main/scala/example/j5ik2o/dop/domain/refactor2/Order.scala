package example.j5ik2o.dop.domain.refactor2

import example.j5ik2o.common.domain.OrderId
import example.j5ik2o.dop.domain.{Money, OrderItem, Quantity}

opaque type Order = Map[String, Any]
opaque type OrderId = String

object OrderId {
  def apply(value: String): OrderId = value

  def toString(self: OrderId): String = self
}

object Order {

  def apply(id: String, orderItems: Vector[OrderItem]): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def id(self: Order): OrderId = self("id").asInstanceOf[OrderId]

  def totalPrice(self: Order): Money = orderItems(self)
    .foldLeft(Money.zero()) { (acc, orderItem) =>
      val item = OrderItem.item(orderItem)
      val quantity = OrderItem.quantity(orderItem)
      Money.plus(acc, Money.times(PriceCalculator.adjustPrice(item), Quantity.value(quantity)))
    }

  def orderItems(self: Order): Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]

}
