package example.j5ik2o.oop.domain

case class CartItemId(value: Int) {
  require(value > 0, "value must be positive")
}
