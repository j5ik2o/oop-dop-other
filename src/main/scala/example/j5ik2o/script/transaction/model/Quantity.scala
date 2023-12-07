package example.j5ik2o.script.transaction.model

opaque type Quantity = Int

object Quantity {
  def apply(value: Int): Quantity = value

  def unapply(quantity: Quantity): Option[Int] = Some(quantity.value)

  extension (quantity: Quantity) {
    def value: Int = quantity
  }
}
