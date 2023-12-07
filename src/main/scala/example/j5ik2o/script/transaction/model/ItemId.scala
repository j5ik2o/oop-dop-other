package example.j5ik2o.script.transaction.model

opaque type ItemId = String

object ItemId {
  def apply(value: String): ItemId = value

  def unapply(itemId: ItemId): Option[String] = Some(itemId)

  def toString(itemId: ItemId): String = itemId
}
