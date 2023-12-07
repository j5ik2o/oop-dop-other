package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.ItemType

opaque type CartItem = Map[String, Any]
opaque type CartItemId = String

object CartItemId {
  def apply(value: String): CartItemId = value

  def unapply(self: CartItemId): Option[String] = Some(self)

  def toString(self: CartItemId): String = self
}

object CartItem {

  def apply(id: CartItemId, item: Item, quantity: Quantity): CartItem =
    Map("id" -> id, "item" -> item, "quantity" -> quantity)

  def id(self: CartItem): CartItemId = self("id").asInstanceOf[CartItemId]

  def item(self: CartItem): Item = self("item").asInstanceOf[Item]

  def quantity(self: CartItem): Quantity = self("quantity").asInstanceOf[Quantity]
}
