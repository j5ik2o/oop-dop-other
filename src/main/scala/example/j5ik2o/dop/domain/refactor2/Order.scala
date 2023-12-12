package example.j5ik2o.dop.domain.refactor2

import example.j5ik2o.dop.domain.{Money, OrderItems}

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

  def apply(id: String, orderItems: OrderItems): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def unapply(self: Order): Option[(OrderId, OrderItems)] =
    Some((self.id, self.orderItems))

  extension (self: Order) {
    def id: OrderId = self("id").asInstanceOf[OrderId]
    def orderItems: OrderItems = self("orderItems").asInstanceOf[OrderItems]
    def totalPrice: Money = orderItems.calculateTotalPrice(PriceCalculator.adjustPrice)
  }

}
