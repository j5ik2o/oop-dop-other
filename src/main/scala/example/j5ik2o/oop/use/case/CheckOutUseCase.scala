package example.j5ik2o.oop.use.`case`

import example.j5ik2o.common.domain.CartId
import example.j5ik2o.oop.domain.{ CartRepository, OrderRepository }
import example.j5ik2o.oop.infrastructure.PaymentGateway

class CheckOutUseCase(
    val cartRepository: CartRepository,
    val orderRepository: OrderRepository,
    val paymentGateway: PaymentGateway
) {
  def execute(cartId: CartId): Unit = {
    val cart  = cartRepository.findById(cartId)
    val order = cart.getOrder
    paymentGateway.pay(order.totalPrice)
    orderRepository.store(order)
  }
}
