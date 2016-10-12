package aux

import explicit.{ UnwrapAux, Unwrap }
import org.scalatest.{ FlatSpec, Matchers }

class ExplicitAuxSuite extends FlatSpec with Matchers {

  def noAux[T[_], R](input: T[R])(
    implicit
    unwrap: Unwrap[T, R]
  ): unwrap.Out = {
    unwrap(input)
  }

  def withAux[T[_], R, Out](input: T[R])(
    implicit
    unwrap: UnwrapAux[T, R, Out],
    printer: Printer[Out]
  ): (String, Out) = {
    printer(unwrap(input))
  }

  "Explicit Aux" should "return unwrapped value" in {

    noAux(List(1, 2, 10, 9)) should be(10)
    noAux(List("1", "2", "10", "9")) should be(4)

    withAux(List(1, 2, 10, 9)) should be("Int: 10", 10)
    withAux(List("1", "2", "10", "9")) should be(("Int: 4", 4))
  }
}
