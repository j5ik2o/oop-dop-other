package example.j5ik2o.dop.domain

opaque type Cart   = Map[String, Any]
opaque type CartId = String

object CartId {
  def apply(value: String): CartId          = value
  def unapply(self: CartId): Option[String] = Some(self)

  given Conversion[String, CartId] = CartId(_)

  extension (self: CartId) {
    def value: String = self
  }
}

object Cart {

  def apply(id: CartId, name: String, cartItems: CartItems): Cart =
    Map("id" -> id, "name" -> name, "cartItems" -> cartItems, "checkOuted" -> false)

  def unapply(self: Cart): Option[(CartId, String, CartItems)] =
    Some((self.id, self.name, self.cartItems))

  extension (self: Cart) {
    def id: CartId = self("id").asInstanceOf[CartId]

    def cartItems: CartItems = self("cartItems").asInstanceOf[CartItems]
    def name: String = self("name").asInstanceOf[String]
    def isCheckOuted: Boolean = self("checkOuted").asInstanceOf[Boolean]

    def add(cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
      val cartItem = CartItem(cartItemId, item, quantity)
      self + ("cartItems" -> (cartItems :+ cartItem))
    }

    def remove(cartItemId: CartItemId): Cart =
      self + ("cartItems" -> (cartItems :- cartItemId))

    def checkOut: (Order, Cart) =
      (Order(OrderId(self.id), cartItems.toOrderItems), self + ("checkOuted" -> true))

  }

}
