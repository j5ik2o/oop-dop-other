package example.j5ik2o.script.transaction.model

opaque type CartItemId = String

object CartItemId {
  def apply(value: String): CartItemId = value

  def unapply(self: CartItemId): Option[String] = Some(self)

  extension (self: CartItemId) {
    def toString: String = self
  }
}
