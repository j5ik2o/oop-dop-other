package example.j5ik2o.script.transaction.model

opaque type OrderItemId = String

object OrderItemId {
  def apply(value: String): OrderItemId = value

  def unapply(orderItemId: OrderItemId): Option[String] = Some(orderItemId)

  extension (orderItemId: OrderItemId) {
    def toString: String = orderItemId
  }
}
