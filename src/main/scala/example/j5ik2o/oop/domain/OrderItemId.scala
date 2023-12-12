package example.j5ik2o.oop.domain

class OrderItemId(value: String)

object OrderItemId {

  given Conversion[String, OrderItemId] = OrderItemId(_)

}
