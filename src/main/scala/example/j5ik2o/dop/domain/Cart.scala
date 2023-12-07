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

  extension (self: Cart) {
    def id: CartId = self("id").asInstanceOf[CartId]

    def cartItems: Seq[CartItem] = self("items").asInstanceOf[Seq[CartItem]]
    def name: String = self("name").asInstanceOf[String]

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
      val orderItems = cartItems.map { cartItem =>
        val cartItemId = cartItem.id
        OrderItem(cartItemId.asInstanceOf[OrderItemId], cartItem.item, cartItem.quantity)
      }.toVector
      (Order(id.asInstanceOf[OrderId], orderItems), self + ("checkOuted" -> true))
    }

  }

}
