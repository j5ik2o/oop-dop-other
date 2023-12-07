package example.j5ik2o.dop.domain.refactor1

import example.j5ik2o.dop.domain.{Money, OrderItem, Quantity}

type Order = Map[String, Any]
type OrderId = String

object OrderId {
  def apply(value: String): OrderId = value

  def toString(self: OrderId): String = self
}

object Order {

  def apply(id: OrderId, orderItems: Vector[OrderItem]): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def id(self: Order): OrderId = self("id").asInstanceOf[OrderId]

  def totalPrice(self: Order): Money = orderItems(self)
    .foldLeft(Money.zero()) { (acc, orderItem) =>
      val item = orderItem.item
      val quantity = orderItem.quantity
      acc + PriceCalculator.adjustPrice(item) * quantity.value
    }

  def orderItems(self: Order): Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]

}
