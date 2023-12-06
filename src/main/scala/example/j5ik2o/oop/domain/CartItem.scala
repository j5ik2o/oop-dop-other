package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.{CartItemId, Quantity}

final case class CartItem(id: CartItemId, item: Item, quantity: Quantity)
