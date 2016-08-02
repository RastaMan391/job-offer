import org.scalatest.FunSuite
import Poker._

class PokerTest extends FunSuite {
  test("Straight flush!")(assert(check(List(new Card(10,2),new Card(9,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "Straight flush!"))

  test("Four of kind")(assert(check(List(new Card(10,2),new Card(10,2), new Card(10,2), new Card(10,2), new Card(6, 3))) == "Four of kind"))

  test("Full house")(assert(check(List(new Card(10,2),new Card(10,2), new Card(10,2), new Card(8,3), new Card(8, 3))) == "Full house"))

  test("Flush")(assert(check(List(new Card(10,2),new Card(10,2), new Card(10,2), new Card(7,2), new Card(8, 2))) == "Flush"))

  test("Straight")(assert(check(List(new Card(10,2),new Card(9,2), new Card(8,2), new Card(7,3), new Card(6, 3))) == "Straight"))

  test("Three of kind")(assert(check(List(new Card(10,1),new Card(10,2), new Card(10,2), new Card(14,3), new Card(8, 3))) == "Three of kind"))

  test("Two pair")(assert(check(List(new Card(10,1),new Card(10,2), new Card(8,2), new Card(8,3), new Card(6, 3))) == "Two pair"))

  test("One pair")(assert(check(List(new Card(12,2),new Card(12,2), new Card(8,2), new Card(7,3), new Card(6, 3))) == "One pair"))

  test("High Card: Ace")(assert(check(List(new Card(14,1),new Card(9,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: Ace"))

  test("High Card: King")(assert(check(List(new Card(10,1),new Card(13,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: King"))

  test("High Card: Queen")(assert(check(List(new Card(10,1),new Card(4,2), new Card(12,2), new Card(7,2), new Card(6, 2))) == "High Card: Queen"))

  test("High Card: Jack")(assert(check(List(new Card(10,1),new Card(11,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: Jack"))

  test("High Card: 10")(assert(check(List(new Card(10,1),new Card(2,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: 10"))

  test("High Card: 9")(assert(check(List(new Card(3,1),new Card(9,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: 9"))

  test("High Card: 8")(assert(check(List(new Card(3,1),new Card(2,2), new Card(8,2), new Card(7,2), new Card(6, 2))) == "High Card: 8"))

  test("High Card: 7")(assert(check(List(new Card(3,1),new Card(2,2), new Card(4,2), new Card(7,2), new Card(6, 2))) == "High Card: 7"))

}
