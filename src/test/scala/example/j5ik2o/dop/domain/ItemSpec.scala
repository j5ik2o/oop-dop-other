package example.j5ik2o.dop.domain

import org.scalatest.freespec.AnyFreeSpec

class ItemSpec extends AnyFreeSpec {
  "Item" - {
    "create" in {
      val genericItem: GenericItem = GenericItem("1", "name", 100)
      assert(genericItem.id.value == "1")
      val item: Item = genericItem
      assert(item.id.value == "1")
    }
  }
}
