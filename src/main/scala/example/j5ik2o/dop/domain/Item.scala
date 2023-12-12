package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.ItemType

import java.net.URL

opaque type Item   = Map[String, Any]
opaque type ItemId = String

object ItemId {
  def apply(value: String): ItemId          = value
  def unapply(self: ItemId): Option[String] = Some(self)

  given Conversion[String, ItemId] = ItemId(_)

  extension (self: ItemId) {
    def value: String = self
  }
}

object Item {

  def apply(id: ItemId, name: ItemName, price: Money, itemType: ItemType): Item =
    Map("id" -> id, "name" -> name, "price" -> price, "type" -> itemType)

  extension (self: Item) {
    def id: ItemId         = self("id").asInstanceOf[ItemId]
    def name: ItemName     = self("name").asInstanceOf[ItemName]
    def price: Money       = self("price").asInstanceOf[Money]
    def itemType: ItemType = self("type").asInstanceOf[ItemType]
  }

}

opaque type GenericItem = Map[String, Any]

object GenericItem {

  def apply(id: ItemId, name: ItemName, price: Money): GenericItem =
    Item.apply(id, name, price, ItemType.Generic)

  def unapply(self: GenericItem): Option[(ItemId, ItemName, Money)] =
    Some((Item.id(self), Item.name(self), Item.price(self)))

  extension (self: GenericItem) {
    def id: ItemId = Item.id(self)
    def name: ItemName = Item.name(self)
    def price: Money = Item.price(self)
  }

  given Conversion[GenericItem, Item] = _.asInstanceOf[Item]

}

opaque type DownloadableItem = Map[String, Any]

object DownloadableItem {

  def apply(id: ItemId, name: ItemName, url: URL, price: Money): DownloadableItem =
    Item.apply(id, name, price, ItemType.Download) + ("url" -> url)

  def unapply(self: DownloadableItem): Option[(ItemId, ItemName, URL, Money)] =
    Some(
      (
        Item.id(self),
        Item.name(self),
        self.url,
        Item.price(self)
      )
    )

  given Conversion[DownloadableItem, Item] = _.asInstanceOf[Item]

  extension (self: DownloadableItem) {
    def id: ItemId = Item.id(self)
    def name: ItemName = Item.name(self)
    def url: URL = self("url").asInstanceOf[URL]
    def price: Money = Item.price(self)
  }
}

opaque type CarItem = Map[String, Any]

object CarItem {

  def apply(id: ItemId, name: ItemName, price: Money): CarItem =
    Item.apply(id, name, price, ItemType.Car)

  def unapply(self: CarItem): Option[(ItemId, ItemName, Money)] =
    Some((Item.id(self), Item.name(self), Item.price(self)))

  given Conversion[CarItem, Item] = _.asInstanceOf[Item]
}
