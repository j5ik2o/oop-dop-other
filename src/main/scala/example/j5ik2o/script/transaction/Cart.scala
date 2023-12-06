package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.CartId

final case class Cart(id: CartId, cartItems: Vector[CartItem])
