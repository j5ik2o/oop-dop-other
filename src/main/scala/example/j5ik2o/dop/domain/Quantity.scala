package example.j5ik2o.dop.domain

opaque type Quantity = Map[String, Any]

object Quantity {

  def apply(value: Int): Quantity = {
    require(value >= 0)
    Map("value" -> value)
  }

  def unapply(self: Quantity): Option[Int] = Some(self.value)

  given Ordering[Quantity] = (x: Quantity, y: Quantity) => {
    x.value.compare(y.value)
  }

  given Conversion[Int, Quantity] = Quantity(_)

  extension (self: Quantity) {
    def value: Int = self("value").asInstanceOf[Int]
  }

}
