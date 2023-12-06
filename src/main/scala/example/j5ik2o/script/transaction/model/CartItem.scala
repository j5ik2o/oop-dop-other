package example.j5ik2o.script.transaction.model

import example.j5ik2o.common.domain.{CartItemId, Quantity}

final case class CartItem(id: CartItemId, item: Item, quantity: Quantity)
