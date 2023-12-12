package example.j5ik2o.dop.domain

opaque type ItemName = String

object ItemName {
  def apply(value: String): ItemName              = value
  def unapply(itemName: ItemName): Option[String] = Some(itemName)

  given Conversion[String, ItemName] = ItemName(_)
}
