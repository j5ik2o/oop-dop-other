package example.j5ik2o.script.transaction.dao

import example.j5ik2o.common.domain.OrderId
import example.j5ik2o.script.transaction.model.Order

trait OrderDao {

  def findById(id: OrderId): Order

  def insert(order: Order): Unit

  def update(order: Order): Unit
}
