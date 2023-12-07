package example.j5ik2o.oop.domain

import scala.annotation.targetName

final case class OrderItems(values: Vector[OrderItem]) {

  @targetName("add")
  def :+(item: OrderItem): OrderItems = OrderItems(values :+ item)

  @targetName("remove")
  def :-(itemId: OrderItemId): OrderItems = OrderItems(values.filterNot(_.id == itemId))

  def toVector: Vector[OrderItem] = values

  def calculateTotalPrice(adjustPrice: Item => Money = _.price): Money =
    values.foldLeft(Money.zero()) { (acc, orderItem) =>
      acc + adjustPrice(orderItem.item) * orderItem.quantity.value
    }
}
