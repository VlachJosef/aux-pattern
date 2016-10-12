package aux

package explicit {

  trait Unwrap[T[_], R] {
    type Out
    def apply(tr: T[R]): Out
  }

  trait UnwrapAux[T[_], R, Out] {
    def apply(tr: T[R]): Out
  }

  object Unwrap {
    implicit def unwrap[T[_], R, Out0](implicit unwrapAux: UnwrapAux[T, R, Out0]) = new Unwrap[T, R] {
      type Out = Out0
      def apply(tr: T[R]): Out = unwrapAux(tr)
    }
  }

  object UnwrapAux {
    implicit object listStringSize extends UnwrapAux[List, String, Int] {
      def apply(tr: List[String]): Int = tr.size
    }

    implicit object listIntMax extends UnwrapAux[List, Int, Int] {
      def apply(tr: List[Int]): Int = tr.max
    }
  }
}

package compact {

  trait Unwrap[T[_], R] {
    type Out
    def apply(tr: T[R]): Out
  }

  object Unwrap {

    type Aux[T[_], R, Out0] = Unwrap[T, R] { type Out = Out0 }

    implicit object listStringSize extends Unwrap[List, String] {
      type Out = Int
      def apply(tr: List[String]): Int = tr.size
    }

    implicit object listIntMax extends Unwrap[List, Int] {
      type Out = Int
      def apply(tr: List[Int]): Int = tr.max
    }
  }
}
