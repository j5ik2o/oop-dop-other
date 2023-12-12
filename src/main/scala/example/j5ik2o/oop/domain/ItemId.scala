package example.j5ik2o.oop.domain

final case class ItemId(value: String)

object ItemId {

  given Conversion[String, ItemId] = ItemId(_)

}
