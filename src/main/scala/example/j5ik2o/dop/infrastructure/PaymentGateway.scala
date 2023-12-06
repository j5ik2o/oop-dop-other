package example.j5ik2o.dop.infrastructure

import example.j5ik2o.dop.domain.Price

trait PaymentGateway {
  def pay(price: Price): Unit
}
