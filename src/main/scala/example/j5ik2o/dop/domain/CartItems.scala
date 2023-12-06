package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.CartItemId
import example.j5ik2o.dop.domain.{ CartItem, CartItems }

final case class CartItems(values: Vector[CartItem])

object CartItems {

  def add(self: CartItems, cartItem: CartItem): CartItems =
    CartItems(self.values :+ cartItem)

  def remove(self: CartItems, cartItemId: CartItemId): CartItems =
    CartItems(self.values.filterNot(_ == cartItemId))

  def toVector(self: CartItems): Vector[CartItem] = self.values

}
