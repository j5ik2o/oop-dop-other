package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.CartItemId

import scala.annotation.targetName

final case class CartItems(values: Vector[CartItem]) {

  @targetName("add")
  def :+(cartItem: CartItem): CartItems =
    CartItems(values :+ cartItem)

  @targetName("remove")
  def :-(cartItemId: CartItemId): CartItems =
    CartItems(values.filterNot(_ == cartItemId))

  def toVector: Vector[CartItem] = values
}
