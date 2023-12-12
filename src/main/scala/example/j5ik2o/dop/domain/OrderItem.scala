package example.j5ik2o.dop.domain

opaque type OrderItem   = Map[String, Any]
opaque type OrderItemId = String

object OrderItemId {
  def apply(value: String): OrderItemId          = value
  def unapply(self: OrderItemId): Option[String] = Some(self)

  extension (self: OrderItemId) {
    def value: String = self
  }
}

object OrderItem {

  def apply(id: OrderItemId, item: Item, quantity: Quantity): OrderItem =
    Map("id" -> id, "item" -> item, "quantity" -> quantity)

  def unapply(self: OrderItem): Option[(OrderItemId, Item, Quantity)] =
    Some((self.id, self.item, self.quantity))

  extension (self: OrderItem) {
    def id: OrderItemId    = self("id").asInstanceOf[OrderItemId]
    def item: Item         = self("item").asInstanceOf[Item]
    def quantity: Quantity = self("quantity").asInstanceOf[Quantity]
  }

}
