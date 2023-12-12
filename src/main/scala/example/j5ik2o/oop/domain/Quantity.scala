package example.j5ik2o.oop.domain

final case class Quantity(value: Int) {
  require(value >= 0, "Quantity must be positive")
}

object Quantity {
  val zero: Quantity = Quantity(0)

  given Conversion[Int, Quantity] = Quantity(_)
}
