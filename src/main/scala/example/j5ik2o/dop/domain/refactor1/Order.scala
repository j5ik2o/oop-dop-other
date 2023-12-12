package example.j5ik2o.dop.domain.refactor1

import example.j5ik2o.dop.domain.{ Money, OrderItem, Quantity }

opaque type Order   = Map[String, Any]
opaque type OrderId = String

object OrderId {
  def apply(value: String): OrderId = value

  def unapply(self: OrderId): Option[String] = Some(self)

  extension (self: OrderId) {
    def value: String = self
  }
}

object Order {

  def apply(id: OrderId, orderItems: Vector[OrderItem]): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def unapply(self: Order): Option[(OrderId, Vector[OrderItem])] =
    Some((self.id, self.orderItems))

  extension (self: Order) {
    def id: OrderId                   = self("id").asInstanceOf[OrderId]
    def orderItems: Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]
    def totalPrice: Money = orderItems
      .foldLeft(Money.zero()) { (acc, orderItem) =>
        val item     = orderItem.item
        val quantity = orderItem.quantity
        acc + PriceCalculator.adjustPrice(item) * quantity.value
      }
  }

}
