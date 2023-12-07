package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.ItemType

opaque type Order = Map[String, Any]
opaque type OrderId = String

object OrderId {
  def apply(value: String): OrderId = value

  def unapply(self: OrderId): Option[String] = Some(self)

  def toString(self: OrderId): String = self
}

object Order {

  def apply(id: OrderId, orderItems: Vector[OrderItem]): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def id(self: Order): OrderId = self("id").asInstanceOf[OrderId]

  def totalPrice(self: Order): Money = orderItems(self).foldLeft(Money.zero()) { (acc, orderItem) =>
    val item = OrderItem.item(orderItem)
    val quantity = OrderItem.quantity(orderItem)
    Money.plus(acc, Money.times(adjustPrice(item), Quantity.value(quantity)))
  }

  def orderItems(self: Order): Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]

  // 送料や割引や税率を考慮した価格を返す
  // - 商品種別がダウンロードの場合は送料無料
  // - 商品種別が車の場合は送料が50000円
  // - 商品種別がダウンロード及び車以外の場合は商品価格の10%が送料
  // - 商品の価格が10000円以上なら10%割引
  // - 商品の価格が5000円以上なら5%割引
  // - 商品の価格が5000円未満なら割引なし
  // - 税率は商品価格の10%。ただし車の場合は贅沢税が20%掛かる
  private def adjustPrice(item: Item): Money = {
    val price = Item.price(item)
    val shippingCost = Item.itemType(item) match {
      case ItemType.Download => Money.zero()
      case ItemType.Car => Money(50000)
      case _ => Money.times(price, 0.1)
    }
    val discount = Money.amount(price) match {
      case amount if amount >= 10000 => Money.times(price, 0.1)
      case amount if amount >= 5000 => Money.times(price, 0.05)
      case _ => Money.zero()
    }
    val tax = Item.itemType(item) match {
      case ItemType.Car => Money.times(price, 0.2)
      case _ => Money.times(price, 0.1)
    }
    Money.plus(Money.minus(Money.plus(price, shippingCost), discount), tax)
  }
}
