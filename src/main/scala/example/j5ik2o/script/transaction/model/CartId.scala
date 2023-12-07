package example.j5ik2o.script.transaction.model

opaque type CartId = String

object CartId {
  def apply(value: String): CartId = value

  def unapply(cartId: CartId): Option[String] = Some(cartId)

  extension (cartId: CartId) {
    def toString: String = cartId
  }
}
