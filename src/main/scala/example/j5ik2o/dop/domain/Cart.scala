package example.j5ik2o.dop.domain

opaque type Cart = Map[String, Any]
opaque type CartId = String

object CartId {
  def apply(value: String): CartId = value
  def unapply(self: CartId): Option[String] = Some(self)

  extension (self: CartId) {
    def value: String = self
  }
}

object Cart {

  def apply(id: CartId, name: String, cartItems: Vector[CartItem]): Cart =
    Map("id" -> id, "name" -> name, "cartItems" -> cartItems, "checkOuted" -> false)

  def unapply(self: Cart): Option[(CartId, String, Vector[CartItem])] =
    Some((self.id, self.name, self.cartItems))

  extension (self: Cart) {
    def id: CartId = self("id").asInstanceOf[CartId]

    def cartItems: Vector[CartItem] = self("cartItems").asInstanceOf[Vector[CartItem]]
    def name: String = self("name").asInstanceOf[String]
    def isCheckOuted: Boolean = self("checkOuted").asInstanceOf[Boolean]

    def add(cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
      val cartItem = CartItem(cartItemId, item, quantity)
      self + ("cartItems" -> (cartItems :+ cartItem))
    }

    def remove(cartItemId: CartItemId): Cart = {
      self + ("cartItems" -> cartItems.filterNot {
        _.id == cartItemId
      })
    }

    def checkOut: (Order, Cart) = {
      val orderId = Cart.id(self).value
      val orderItems = cartItems.map { cartItem =>
        val cartItemId = cartItem.id
        OrderItem(OrderItemId(cartItemId.value), cartItem.item, cartItem.quantity)
      }
      (Order(OrderId(orderId), orderItems), self + ("checkOuted" -> true))
    }

  }

}
