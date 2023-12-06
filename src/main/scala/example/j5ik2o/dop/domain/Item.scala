package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.{ ItemId, ItemName, ItemType }
import example.j5ik2o.dop.domain.Price

final case class Item(id: ItemId, itemType: ItemType, name: ItemName, price: Price)
