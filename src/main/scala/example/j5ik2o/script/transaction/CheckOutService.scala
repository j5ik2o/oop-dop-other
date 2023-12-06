package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.{CartId, OrderId, OrderItemId, Quantity}

import scala.collection.mutable.ArrayBuffer

class CheckOutService(val cartDao: CartDao, val orderDao: OrderDao, val paymentGateway: PaymentGateway) {
  def checkOut(cartId: CartId): Unit = {
    val cart = cartDao.findById(cartId)
    val orderItems = ArrayBuffer.empty[OrderItem]
    for (cartItem <- cart.cartItems) {
      OrderItem(OrderItemId(cartItem.id.value), cartItem.item, cartItem.quantity)
    }
    val order = Order(OrderId(cartId.value), orderItems.toVector)
    var totalPrice = Price.zero()
    for (orderItem <- order.orderItems) {
      totalPrice += orderItem.item.price * Quantity(orderItem.quantity.value)
    }
    paymentGateway.pay(totalPrice)
    orderDao.insert(order)
  }
}
