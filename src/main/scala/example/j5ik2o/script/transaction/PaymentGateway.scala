package example.j5ik2o.script.transaction

trait PaymentGateway {
  def pay(price: Price): Unit
}
