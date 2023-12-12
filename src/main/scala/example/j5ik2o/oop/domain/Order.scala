package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.ItemType

final case class Order(id: OrderId, orderItems: OrderItems) {

  def totalPrice: Money = orderItems.calculateTotalPrice(adjustPrice)

  // 送料や割引や税率を考慮した価格を返す
  // - 商品種別がダウンロードの場合は送料無料
  // - 商品種別が車の場合は送料が50000円
  // - 商品種別がダウンロード及び車以外の場合は商品価格の10%が送料
  // - 商品の価格が10000円以上なら10%割引
  // - 商品の価格が5000円以上なら5%割引
  // - 商品の価格が5000円未満なら割引なし
  // - 税率は商品価格の10%。ただし車の場合は贅沢税が20%掛かる
  private def adjustPrice(item: Item): Money = {
    val price = item.price
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
