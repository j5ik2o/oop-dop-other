package example.j5ik2o.dop.domain

import example.j5ik2o.common.domain.CartId

trait CartRepository {

  def findById(id: CartId): Cart
  def store(cart: Cart): Unit

}
