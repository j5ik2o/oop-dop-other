package example.j5ik2o.script.transaction.service

import example.j5ik2o.script.transaction.model.Money

trait PaymentService {
  def pay(price: Money): Unit
}
