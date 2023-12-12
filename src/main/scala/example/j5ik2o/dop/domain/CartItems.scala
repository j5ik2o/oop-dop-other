package example.j5ik2o.dop.domain

opaque type CartItems = Vector[CartItem]

object CartItems {
  def apply(items: CartItem*): CartItems                  = Vector(items: _*)
  def unapply(items: CartItems): Option[Vector[CartItem]] = Some(items)

  extension (cartItems: CartItems) {
    infix def :+(cartItem: CartItem): CartItems = cartItems :+ cartItem
    infix def :-(cartItemId: CartItemId): CartItems = cartItems.filterNot(_.id == cartItemId)
    def toOrderItems: OrderItems = OrderItems(cartItems.map { cartItem =>
      val cartItemId = cartItem.id
      OrderItem(OrderItemId(cartItemId.value), cartItem.item, cartItem.quantity)
    }: _*)
  }
}
