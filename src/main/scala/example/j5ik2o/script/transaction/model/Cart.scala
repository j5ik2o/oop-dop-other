package example.j5ik2o.script.transaction.model

import example.j5ik2o.common.domain.CartId

final case class Cart(id: CartId, cartItems: Vector[CartItem], checkOuted: Boolean = false)
