package example.j5ik2o.oop.infrastructure

import example.j5ik2o.oop.domain.Money

trait PaymentGateway {
  def pay(price: Money): Unit
}
