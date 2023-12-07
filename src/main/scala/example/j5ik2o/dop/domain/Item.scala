package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.ItemType

opaque type Item = Map[String, Any]
opaque type ItemId = String

object ItemId {
  def apply(value: String): ItemId = value
  def unapply(self: ItemId): Option[String] = Some(self)

  extension (self: ItemId) {
    def value: String = self
  }
}

object Item {

  extension (self: Item) {
    def id: ItemId = self("id").asInstanceOf[ItemId]
    def name: String = self("name").asInstanceOf[String]
    def price: Money = self("price").asInstanceOf[Money]
    def itemType: ItemType = self("type").asInstanceOf[ItemType]
  }

}

opaque type GenericItem = Map[String, Any]

object GenericItem {

  def apply(id: ItemId, name: String, price: Money): GenericItem =
    Map("id" -> id, "name" -> name, "price" -> price, "type" -> ItemType.Generic)

  def unapply(self: GenericItem): Option[(ItemId, String, Money)] =
    Some((self("id").asInstanceOf[ItemId], self("name").asInstanceOf[String], self("price").asInstanceOf[Money]))

}

opaque type DownloadableItem = Map[String, Any]

object DownloadableItem {

  def apply(id: ItemId, name: String, url: String, price: Money): DownloadableItem =
    Map("id" -> id, "name" -> name, "url" -> url, "price" -> price, "type" -> ItemType.Download)

  def unapply(self: DownloadableItem): Option[(ItemId, String, String, Money)] =
    Some(
      (
        self("id").asInstanceOf[ItemId],
        self("name").asInstanceOf[String],
        self("url").asInstanceOf[String],
        self(
          "price"
        ).asInstanceOf[Money]
      )
    )

  extension (self: DownloadableItem) {
    def url: String = self("url").asInstanceOf[String]
  }
}

opaque type CarItem = Map[String, Any]

object CarItem {

  def apply(id: ItemId, name: String, price: Money): CarItem =
    Map("id" -> id, "name" -> name, "price" -> price, "type" -> ItemType.Car)

  def unapply(self: CarItem): Option[(ItemId, String, Money)] =
    Some((self("id").asInstanceOf[ItemId], self("name").asInstanceOf[String], self("price").asInstanceOf[Money]))

}
