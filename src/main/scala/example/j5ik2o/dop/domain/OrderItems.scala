package example.j5ik2o.dop.domain

import scala.annotation.targetName

opaque type OrderItems = Vector[OrderItem]

object OrderItems {
  def apply(items: OrderItem*): OrderItems                  = Vector(items: _*)
  def unapply(items: OrderItems): Option[Vector[OrderItem]] = Some(items)

  extension (items: OrderItems) {
    @targetName("add")
    infix def :+(item: OrderItem): OrderItems = items :+ item
    def toVector: Vector[OrderItem]           = items

    def calculateTotalPrice(adjustPrice: Item => Money = _.price): Money =
      items.foldLeft(Money.zero()) { (acc, orderItem) =>
        acc + adjustPrice(orderItem.item) * orderItem.quantity.value
      }
  }
}
