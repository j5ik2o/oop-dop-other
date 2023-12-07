package example.j5ik2o.dop.domain

trait OrderRepository {
  def findById(id: OrderId): Order
  def store(order: Order): Unit
}
