package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.OrderItemId
import example.j5ik2o.dop.domain.{ Item, OrderItem, OrderItems, Price }

final case class OrderItems(values: Vector[OrderItem])

object OrderItems {
  def add(self: OrderItems, item: OrderItem): OrderItems = OrderItems(self.values :+ item)

  def remove(self: OrderItems, itemId: OrderItemId): OrderItems = OrderItems(self.values.filterNot(_.id == itemId))

  def toVector(self: OrderItems): Vector[OrderItem] = self.values

  def calculateTotalPrice(self: OrderItems, adjustPrice: Item => Price = _.price): Price =
    self.values.foldLeft(Price.zero()) { (acc, orderItem) =>
      Price.plus(acc, Price.times(adjustPrice(orderItem.item), orderItem.quantity))
    }
}
