package example.j5ik2o.oop.domain

final case class ItemName(value: String)

object ItemName {

  given Conversion[String, ItemName] = ItemName(_)

}
