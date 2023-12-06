package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.*
import example.j5ik2o.oop.*

final case class Cart(id: CartId, cartItems: CartItems, checkOuted: Boolean = false) {

  def add(cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
    val cartItem = CartItem(cartItemId, item, quantity)
    copy(cartItems = cartItems :+ cartItem)
  }

  def remove(cartItemId: CartItemId): Cart = {
    copy(cartItems = cartItems :- cartItemId)
  }

  def checkOut: (Order, Cart) = {
    val orderItems = cartItems.toVector.map { cartItem =>
      OrderItem(OrderItemId(cartItem.id.value), cartItem.item, cartItem.quantity)
    }
    (Order(OrderId(id.value), OrderItems(orderItems)), copy(checkOuted = true))
  }

}
