package example.j5ik2o.dop.domain

opaque type CartItems = Vector[CartItem]

object CartItems {
  def apply(items: CartItem*): CartItems                  = Vector(items: _*)
  def unapply(items: CartItems): Option[Vector[CartItem]] = Some(items)

  extension (cartItems: CartItems) {
    infix def :+(cartItem: CartItem): CartItems = cartItems :+ cartItem
    def toVector: Vector[CartItem]              = cartItems
  }
}
