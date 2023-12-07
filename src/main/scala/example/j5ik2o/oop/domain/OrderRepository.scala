package example.j5ik2o.oop.domain

trait OrderRepository {

  def findById(id: OrderId): Order
  def store(order: Order): Unit
}
