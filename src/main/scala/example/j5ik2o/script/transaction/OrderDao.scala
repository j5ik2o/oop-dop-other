package example.j5ik2o.script.transaction

import example.j5ik2o.common.domain.OrderId

trait OrderDao {

  def findById(id: OrderId): Order

  def insert(order: Order): Unit
}
