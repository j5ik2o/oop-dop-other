package example.j5ik2o.common.domain

case class CartItemId(value: Int) {
  require(value > 0, "value must be positive")
}
