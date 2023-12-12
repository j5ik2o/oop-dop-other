package example.j5ik2o.dop.use.`case`

import example.j5ik2o.dop.domain.*
import example.j5ik2o.dop.infrastructure.MockPaymentGateway
import org.scalatest.freespec.AnyFreeSpec

import scala.language.implicitConversions

class CheckOutUseCaseSpec extends AnyFreeSpec {
  "CheckOutUseCase" - {
    "execute" in {
      // Given
      val (cartId: CartId, cart: Cart) = newCart
      MockCartRepository.store(cart)
      val useCase = new CheckOutUseCase(MockCartRepository, MockOrderRepository, MockPaymentGateway)

      // When
      val order1 = useCase.execute(cartId)

      // Then
      val order2 = MockOrderRepository.findById(order1.id)
      assert(order2 == order1)
      val cart2 = MockCartRepository.findById(cartId)
      assert(cart2.isCheckOuted)
    }
  }

  private def newCart = {
    val cartId                      = CartId("cart1")
    val name                        = "cart1"
    val cartItem: CartItem          = newCartItem
    val cartItems: Vector[CartItem] = newCartItems(cartItem)
    val cart                        = Cart(cartId, name, cartItems)
    (cartId, cart)
  }

  private def newCartItems(cartItem: CartItem): Vector[CartItem] = {
    val cartItems = Vector(cartItem)
    cartItems
  }

  private def newCartItem: CartItem = {
    val cartItemId = CartItemId("item1")
    val itemId     = ItemId("item1")
    val itemName   = ItemName("item1")
    val item       = GenericItem(itemId, itemName, price = 100)
    val cartItem   = CartItem(cartItemId, item, quantity = 1);
    cartItem
  }
}
