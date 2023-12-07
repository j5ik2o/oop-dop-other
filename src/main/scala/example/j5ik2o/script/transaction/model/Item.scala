package example.j5ik2o.script.transaction.model

import example.j5ik2o.common.domain.ItemType

final case class Item(id: ItemId, itemType: ItemType, name: ItemName, price: Money)
