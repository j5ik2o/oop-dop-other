package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.{ OrderItemId, Quantity }
import example.j5ik2o.dop.domain.Item

final case class OrderItem(id: OrderItemId, item: Item, quantity: Quantity)