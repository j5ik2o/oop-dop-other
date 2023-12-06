package example.j5ik2o.dop.use.`case`

import example.j5ik2o.common.domain.CartId
import example.j5ik2o.dop.domain.Cart
import example.j5ik2o.dop.domain.Order
import example.j5ik2o.dop.domain.{ CartRepository, OrderRepository }
import example.j5ik2o.dop.infrastructure.PaymentGateway

class CheckOutUseCase(
    val cartRepository: CartRepository,
    val orderRepository: OrderRepository,
    val paymentGateway: PaymentGateway
) {
  def execute(cartId: CartId): Unit = {
    val cart  = cartRepository.findById(cartId)
    val order = Cart.getOrder(cart)
    paymentGateway.pay(Order.totalPrice(order))
    orderRepository.store(order)
  }
}
