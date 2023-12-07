package example.j5ik2o.dop.domain.refactor2

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

  extension (self: Order) {
    def id: OrderId = self("id").asInstanceOf[OrderId]

    def totalPrice: Money = orderItems
      .foldLeft(Money.zero()) { (acc, orderItem) =>
        val item = OrderItem.item(orderItem)
        val quantity = OrderItem.quantity(orderItem)
        acc + PriceCalculator.adjustPrice(item) * Quantity.value(quantity)
      }

    def orderItems: Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]
  }

}
