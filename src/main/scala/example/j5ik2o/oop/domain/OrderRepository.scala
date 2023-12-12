package example.j5ik2o.oop.domain

trait OrderRepository {

  def findById(id: OrderId): Order
  def store(order: Order): Unit
}

object MockOrderRepository extends OrderRepository {

  private var orders: Map[OrderId, Order] = Map.empty

  override def findById(id: OrderId): Order = orders(id)

  override def store(order: Order): Unit = orders = orders + (order.id -> order)

}
