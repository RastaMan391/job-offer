object SimpleOOModelling {
  case class Car(brand: String, model: String, volume: String){
    require(volume.length < 4, "Incorrect value of displacement volume")
  }

  def function(cars: Seq[Car]): Seq[Car] = {
    (cars.distinct).sortBy(a => (a.brand, a.model, a.volume))
  }

  def main(args: Array[String]): Unit ={
    val cars = Seq(new Car("Toyota", "Corolla", "1.9"), new Car("Toyota", "Corolla", "1.9"), new Car("Alfa Romeo", "MiTo", "1.4"), new Car("Alfa Romeo", "Giulietta", "1.7"), new Car("Alfa Romeo", "Giulietta", "1.4"))

    println(function(cars))
  }
}
