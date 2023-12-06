package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.{ItemId, ItemName, ItemType}

final case class Item(id: ItemId, itemType: ItemType, name: ItemName, price: Price)
