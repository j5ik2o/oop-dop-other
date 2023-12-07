package example.j5ik2o.dop.domain

trait CartRepository {
  def findById(id: CartId): Cart
  def store(cart: Cart): Unit
}
