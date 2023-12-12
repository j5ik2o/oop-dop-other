package example.j5ik2o.script.transaction.dao

import example.j5ik2o.script.transaction.model.{ Cart, CartId }

trait CartDao {

  def findById(id: CartId): Cart

  def insert(cart: Cart): Unit

  def update(cart: Cart): Unit

}
