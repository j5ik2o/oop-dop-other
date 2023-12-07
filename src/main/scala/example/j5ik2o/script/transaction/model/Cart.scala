package example.j5ik2o.script.transaction.model

final case class Cart(id: CartId, cartItems: Vector[CartItem], checkOuted: Boolean = false)
