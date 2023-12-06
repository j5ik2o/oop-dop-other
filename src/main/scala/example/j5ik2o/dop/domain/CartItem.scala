package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.{ CartItemId, Quantity }
import example.j5ik2o.dop.domain.Item

final case class CartItem(id: CartItemId, item: Item, quantity: Quantity)
