package example.j5ik2o.dop.use.`case`

import example.j5ik2o.dop.domain.*
import example.j5ik2o.dop.infrastructure.PaymentGateway

// カートをチェックアウトするサービス
class CheckOutUseCase(
    val cartRepository: CartRepository,
    val orderRepository: OrderRepository,
    val paymentGateway: PaymentGateway
) {
  def execute(cartId: CartId): Unit = {
    // カートの取得
    val cart = cartRepository.findById(cartId)

    // カートのチェックアウト
    val (order, cartUpdated) = Cart.checkOut(cart)

    // 支払い処理
    paymentGateway.pay(Order.totalPrice(order))

    // 注文の保存
    orderRepository.store(order)
    // カートの更新
    cartRepository.store(cartUpdated)
  }
}
