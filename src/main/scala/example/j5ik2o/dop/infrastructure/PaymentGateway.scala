package example.j5ik2o.dop.infrastructure

import example.j5ik2o.dop.domain.Money

trait PaymentGateway {
  def pay(price: Money): Unit
}
