package example.j5ik2o.script.transaction.model

opaque type ItemName = String

object ItemName {
  def apply(value: String): ItemName = value

  def unapply(self: ItemName): Option[String] = Some(self)

  extension (self: ItemName) {
    def toString: String = self
  }
}
