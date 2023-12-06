package example.j5ik2o.oop.infrastructure

import example.j5ik2o.oop.domain.Price

trait PaymentGateway {
  def pay(price: Price): Unit
}
