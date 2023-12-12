package example.j5ik2o.oop.use.`case`

import example.j5ik2o.oop.domain.{CartId, CartRepository, Order, OrderRepository}
import example.j5ik2o.oop.infrastructure.PaymentGateway

// カートをチェックアウトするサービス
class CheckOutUseCase(
    val cartRepository: CartRepository,
    val orderRepository: OrderRepository,
    val paymentGateway: PaymentGateway
) {
  def execute(cartId: CartId): Order = {
    // カートの取得
    val cart = cartRepository.findById(cartId)

    // カートのチェックアウト
    val (order, cartUpdated) = cart.checkOut
    // 支払い処理
    paymentGateway.pay(order.totalPrice)

    // 注文の保存
    orderRepository.store(order)
    // カートの更新
    cartRepository.store(cartUpdated)

    order
  }
}
