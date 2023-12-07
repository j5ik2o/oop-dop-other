package example.j5ik2o.dop.domain

opaque type Quantity = Map[String, Any]

object Quantity {

  def apply(value: Int): Quantity = {
    require(value >= 0)
    Map("value" -> value)
  }

  extension (self: Quantity) {
    def value: Int = self("value").asInstanceOf[Int]
  }

}
