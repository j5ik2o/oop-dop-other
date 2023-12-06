package example.j5ik2o.oop.domain

import example.j5ik2o.common.domain.OrderId

trait OrderRepository {

  def findById(id: OrderId): Order
  def store(order: Order): Unit
}
