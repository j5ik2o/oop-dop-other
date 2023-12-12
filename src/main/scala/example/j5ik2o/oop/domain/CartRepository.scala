package example.j5ik2o.oop.domain

trait CartRepository {

  def findById(id: CartId): Cart

  def store(cart: Cart): Unit

}

object MockCartRepository extends CartRepository {

  private var carts: Map[CartId, Cart] = Map.empty

  override def findById(id: CartId): Cart = carts(id)

  override def store(cart: Cart): Unit = carts = carts + (cart.id -> cart)

}
