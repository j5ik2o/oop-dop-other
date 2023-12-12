package example.j5ik2o.oop.domain

final case class CartId(value: String)

object CartId {

  given Conversion[String, CartId] = CartId(_)

}
