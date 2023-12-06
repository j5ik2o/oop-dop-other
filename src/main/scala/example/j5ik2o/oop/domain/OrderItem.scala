package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.{OrderItemId, Quantity}

final case class OrderItem(id: OrderItemId, item: Item, quantity: Quantity)
