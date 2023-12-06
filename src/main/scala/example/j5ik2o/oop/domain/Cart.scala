package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.{ CartId, CartItemId, OrderId, OrderItemId, Quantity }
import example.j5ik2o.oop.*

final case class Cart(id: CartId, cartItems: CartItems) {

  def add(cartItemId: CartItemId, item: Item, quantity: Quantity): Cart = {
    val cartItem = CartItem(cartItemId, item, quantity)
    copy(cartItems = cartItems :+ cartItem)
  }

  def remove(cartItemId: CartItemId): Cart = {
    copy(cartItems = cartItems :- cartItemId)
  }

  def getOrder: Order = {
    val orderItems = cartItems.toVector.map { cartItem =>
      OrderItem(OrderItemId(cartItem.id.value), cartItem.item, cartItem.quantity)
    }
    Order(OrderId(id.value), OrderItems(orderItems))
  }

}
