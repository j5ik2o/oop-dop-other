package example.j5ik2o.script.transaction.service

import example.j5ik2o.script.transaction.model.Price

trait PaymentService {
  def pay(price: Price): Unit
}
