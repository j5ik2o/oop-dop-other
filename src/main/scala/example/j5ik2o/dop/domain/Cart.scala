package example.j5ik2o.dop.domain

opaque type Cart = Map[String, Any]
opaque type CartId = String

object CartId {
  def apply(value: String): CartId = value

  def unapply(self: CartId): Option[String] = Some(self)

  def toString(self: CartId): String = self
}

object Cart {

  def apply(id: CartId, name: String, cartItems: Vector[CartItem]): Cart =
    Map("id" -> id, "name" -> name, "cartItems" -> cartItems, "checkOuted" -> false)

  def name(self: Cart): String = self("name").asInstanceOf[String]

  def add(self: Cart, cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
    val cartItem = CartItem(cartItemId, item, quantity)
    self + ("cartItems" -> (cartItems(self) :+ cartItem))
  }

  def remove(self: Cart, cartItemId: CartItemId): Cart = {
    self + ("cartItems" -> cartItems(self).filterNot { cartItem => CartItem.id(cartItem) == cartItemId })
  }

  def checkOut(self: Cart): (Order, Cart) = {
    val orderItems = Cart
      .cartItems(self).map { cartItem =>
        val cartItemId = CartItem.id(cartItem)
        OrderItem(cartItemId.asInstanceOf[OrderItemId], CartItem.item(cartItem), CartItem.quantity(cartItem))
      }.toVector
    val orderId = id(self)
    (Order(orderId.asInstanceOf[OrderId], orderItems), self + ("checkOuted" -> true))
  }

  def id(self: Cart): CartId = self("id").asInstanceOf[CartId]

  def cartItems(self: Cart): Seq[CartItem] = self("items").asInstanceOf[Seq[CartItem]]

}
