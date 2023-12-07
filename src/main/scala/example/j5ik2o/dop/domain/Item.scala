package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.{ItemId, ItemName, ItemType}

sealed trait Item {
  def id: ItemId

  def itemType: ItemType

  def name: ItemName

  def price: Price
}

final case class GenericItem(id: ItemId, name: ItemName, price: Price) extends Item {
  val itemType: ItemType = ItemType.Generic
}

final case class DownloadableItem(id: ItemId, name: ItemName, price: Price) extends Item {
  val itemType: ItemType = ItemType.Download
}

final case class CarItem(id: ItemId, name: ItemName, price: Price) extends Item {
  val itemType: ItemType = ItemType.Car
}

final case class ElectricPartItem(id: ItemId, name: ItemName, price: Price) extends Item {
  val itemType: ItemType = ItemType.ElectricParts
}

final case class ElectricAssemblyPartItem(id: ItemId, name: ItemName, parts: Vector[ElectricPartItem]) extends Item {
  val itemType: ItemType = ItemType.ElectricParts

  override def price: Price = {
    parts.foldLeft(Price.zero()) { (acc, part) =>
      Price.plus(acc, part.price)
    }
  }
}
