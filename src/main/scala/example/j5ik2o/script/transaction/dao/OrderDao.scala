package example.j5ik2o.script.transaction.dao

import example.j5ik2o.script.transaction.model.{Order, OrderId}

trait OrderDao {

  def findById(id: OrderId): Order

  def insert(order: Order): Unit

  def update(order: Order): Unit
}
