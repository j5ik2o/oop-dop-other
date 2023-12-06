package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.OrderId

final case class Order(id: OrderId, orderItems: Vector[OrderItem])
