package example.j5ik2o.script.transaction.model

opaque type OrderId = String

object OrderId {
  def apply(value: String): OrderId = value

  def unapply(value: OrderId): Option[String] = Some(value)

  extension (value: OrderId) {
    def toString: String = value
  }
}
