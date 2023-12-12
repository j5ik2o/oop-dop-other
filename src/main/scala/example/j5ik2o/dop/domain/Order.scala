package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.ItemType

opaque type Order   = Map[String, Any]
opaque type OrderId = String

object OrderId {
  def apply(value: String): OrderId          = value
  def unapply(self: OrderId): Option[String] = Some(self)

  given Conversion[String, OrderId] = OrderId(_)

  extension (self: OrderId) {
    def value: String = self
  }
}

object Order {

  def apply(id: OrderId, orderItems: Vector[OrderItem]): Order =
    Map("id" -> id, "orderItems" -> orderItems)

  def unapply(self: Order): Option[(OrderId, Vector[OrderItem])] =
    Some((self.id, self.orderItems))

  extension (self: Order) {
    def id: OrderId                   = self("id").asInstanceOf[OrderId]
    def orderItems: Vector[OrderItem] = self("orderItems").asInstanceOf[Vector[OrderItem]]
    def totalPrice: Money = orderItems.foldLeft(Money.zero()) { (acc, orderItem) =>
      val item     = orderItem.item
      val quantity = orderItem.quantity
      acc + adjustPrice(item) * quantity.value
    }
  }

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
    val shippingCost = item.itemType match {
      case ItemType.Download => Money.zero()
      case ItemType.Car      => Money(50000)
      case _                 => price * 0.1
    }
    val discount = price.amount match {
      case amount if amount >= 10000 => price * 0.1
      case amount if amount >= 5000  => price * 0.05
      case _                         => Money.zero()
    }
    val tax = item.itemType match {
      case ItemType.Car => price * 0.2
      case _            => price * 0.1
    }
    price + shippingCost - discount + tax
  }
}
