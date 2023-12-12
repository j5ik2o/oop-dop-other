package example.j5ik2o.dop.domain

opaque type CartItem   = Map[String, Any]
opaque type CartItemId = String

object CartItemId {
  def apply(value: String): CartItemId          = value
  def unapply(self: CartItemId): Option[String] = Some(self)

  extension (self: CartItemId) {
    def value: String = self
  }
}

object CartItem {

  def apply(id: CartItemId, item: Item, quantity: Quantity): CartItem =
    Map("id" -> id, "item" -> item, "quantity" -> quantity)

  def unapply(self: CartItem): Option[(CartItemId, Item, Quantity)] =
    Some((self.id, self.item, self.quantity))

  extension (self: CartItem) {
    def id: CartItemId     = self("id").asInstanceOf[CartItemId]
    def item: Item         = self("item").asInstanceOf[Item]
    def quantity: Quantity = self("quantity").asInstanceOf[Quantity]
  }
}
