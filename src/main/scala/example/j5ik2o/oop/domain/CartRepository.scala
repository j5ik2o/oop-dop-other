package example.j5ik2o.oop.domain

trait CartRepository {

  def findById(id: CartId): Cart

  def store(cart: Cart): Unit

}
