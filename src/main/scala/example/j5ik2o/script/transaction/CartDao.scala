package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.CartId

trait CartDao {

  def findById(id: CartId): Cart

  def insert(cart: Cart): Unit

}
