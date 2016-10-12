package aux

/**
 * Type class providing String representation
 * of a value of type T together with the original value
 */
trait Printer[T] {
  def apply(t: T): (String, T)
}

object Printer {
  implicit object stringPrinter extends Printer[String] {
    def apply(s: String): (String, String) = ("String: " + s, s)
  }

  implicit object intPrinter extends Printer[Int] {
    def apply(i: Int): (String, Int) = ("Int: " + i, i)
  }
}
