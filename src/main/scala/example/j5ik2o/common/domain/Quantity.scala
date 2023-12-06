package example.j5ik2o.common.domain

final case class Quantity(value: Int) {
  require(value >= 0, "Quantity must be positive")
}
