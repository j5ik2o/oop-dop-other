package example.j5ik2o.dop.domain

import example.j5ik2o.common.*
import example.j5ik2o.common.domain.*
import example.j5ik2o.dop.*

final case class Cart(id: CartId, cartItems: CartItems, checkOuted: Boolean = false)

object Cart {
  def add(self: Cart, cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
    val cartItem = CartItem(cartItemId, item, quantity)
    self.copy(cartItems = CartItems.add(self.cartItems, cartItem))
  }

  def remove(self: Cart, cartItemId: CartItemId): Cart = {
    self.copy(cartItems = CartItems.remove(self.cartItems, cartItemId))
  }

  def checkOut(self: Cart): (Order, Cart) = {
    val orderItems = CartItems.toVector(self.cartItems).map { cartItem =>
      OrderItem(OrderItemId(cartItem.id.value), cartItem.item, cartItem.quantity)
    }
    (Order(OrderId(self.id.value), OrderItems(orderItems)), self.copy(checkOuted = true))
  }
}
