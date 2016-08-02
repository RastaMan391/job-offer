import org.scalatest.FunSuite
import SimpleOOModelling._

class SimpleOOModellingTest extends FunSuite {
  test("Cars")(assert(function(Seq(new Car("Toyota", "Corolla", "1.9"), new Car("Toyota", "Corolla", "1.9"),new Car("Alfa Romeo", "MiTo", "1.7"), new Car("Alfa Romeo", "MiTo","1.4"), new Car("Ferrari", "458", "5.5"))) == List(Car("Alfa Romeo", "MiTo", "1.4"),Car("Alfa Romeo", "MiTo", "1.7"),Car("Ferrari", "458", "5.5"),Car("Toyota", "Corolla", "1.9"))))
}
