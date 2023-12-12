package example.j5ik2o.script.transaction.service

import example.j5ik2o.script.transaction.dao.{ CartDao, OrderDao }
import example.j5ik2o.script.transaction.model.*

import scala.collection.mutable.ArrayBuffer

// カートをチェックアウトするサービス
class CheckOutService(val cartDao: CartDao, val orderDao: OrderDao, val paymentGateway: PaymentService) {

  def checkOut(cartId: CartId): Unit = {
    // カートの取得
    val cart = cartDao.findById(cartId)

    // 注文の作成
    val orderItems = ArrayBuffer.empty[OrderItem]
    for (cartItem <- cart.cartItems) {
      OrderItem(OrderItemId(cartItem.id.toString), cartItem.item, cartItem.quantity)
    }
    val order = Order(OrderId(cartId.toString), orderItems.toVector)

    // 合計金額の計算
    var totalPrice = Money.zero()
    for (orderItem <- order.orderItems) {
      totalPrice += orderItem.item.price * orderItem.quantity.value
    }
    // 支払い処理
    paymentGateway.pay(totalPrice)

    // 注文の保存
    orderDao.insert(order)
    // カートの更新
    cartDao.update(cart.copy(checkOuted = true))
  }

}
