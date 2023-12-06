package example.j5ik2o.oop.domain.refactor3

import example.j5ik2o.common
import example.j5ik2o.common.domain
import example.j5ik2o.common.domain.ItemType
import example.j5ik2o.oop.domain.{Item, Money, Price}

object PriceCalculator {
  // 送料や割引や税率を考慮した価格を返す
  // - 商品種別がダウンロードの場合は送料無料
  // - 商品種別が車の場合は送料が50000円
  // - 商品種別がダウンロード及び車以外の場合は商品価格の10%が送料
  // - 商品の価格が10000円以上なら10%割引
  // - 商品の価格が5000円以上なら5%割引
  // - 商品の価格が5000円未満なら割引なし
  // - 税率は商品価格の10%。ただし車の場合は贅沢税が20%掛かる
  def adjustPrice(item: Item): Price =
    calculateShippingCost(calculateDiscount(calculateTax(calculateBasePrice)))(item)

  private def calculateBasePrice(item: Item): Price = item.price

  private def calculateShippingCost(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    basePrice + (item.itemType match {
      case ItemType.Download => Price.zero()
      case ItemType.Car      => Price(Money(50000))
      case _                 => item.price * 0.1
    })
  }

  private def calculateDiscount(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    basePrice - (item.price.value.amount match {
      case amount if amount >= 10000 => item.price * 0.1
      case amount if amount >= 5000  => item.price * 0.05
      case _                         => Price.zero()
    })
  }

  private def calculateTax(baseCalculation: Item => Price)(item: Item): Price = {
    val basePrice = baseCalculation(item)
    basePrice + (item.itemType match {
      case domain.ItemType.Car => item.price * 0.2
      case _                           => item.price * 0.1
    })
  }
}
