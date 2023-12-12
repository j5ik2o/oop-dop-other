package example.j5ik2o.oop.domain

case class CartItemId(value: String)

object CartItemId {

  given Conversion[String, CartItemId] = CartItemId(_)

}
