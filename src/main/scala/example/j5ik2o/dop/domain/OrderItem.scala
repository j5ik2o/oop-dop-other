package example.j5ik2o.dop.domain

opaque type OrderItem = Map[String, Any]
opaque type OrderItemId = String

object OrderItemId {
  def apply(value: String): OrderItemId = value

  def unapply(self: OrderItemId): Option[String] = Some(self)

  def toString(self: OrderItemId): String = self
}

object OrderItem {

  def apply(id: OrderItemId, item: Item, quantity: Quantity): OrderItem = {
    Map("id" -> id, "item" -> item, "quantity" -> quantity)
  }

  def id(self: OrderItem): OrderItemId = self("id").asInstanceOf[OrderItemId]

  def item(self: OrderItem): Item = self("item").asInstanceOf[Item]

  def quantity(self: OrderItem): Quantity = self("quantity").asInstanceOf[Quantity]

}
