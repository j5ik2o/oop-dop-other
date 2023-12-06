package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.{ ItemId, ItemName, ItemType }
import example.j5ik2o.oop.domain.Price

final case class Item(id: ItemId, itemType: ItemType, name: ItemName, price: Price)
