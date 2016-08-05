import org.scalatest.FunSuite
import Poker._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PokerTest extends FunSuite {
  test("Straight flush!")(assert(check(List(new Card(Cards.Ten,Color.Diamond),new Card(Cards.Nine,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "Straight flush!"))

  test("Four of kind")(assert(check(List(new Card(Cards.Ten,Color.Club ),new Card(Cards.Ten,Color.Diamond), new Card(Cards.Ten,Color.Heart), new Card(Cards.Ten,Color.Spade), new Card(Cards.Six,Color.Heart))) == "Four of kind"))

  test("Full house")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Ten,Color.Diamond), new Card(Cards.Ten,Color.Heart), new Card(Cards.Eight,Color.Spade), new Card(Cards.Eight,Color.Heart))) == "Full house"))

  test("Flush")(assert(check(List(new Card(Cards.Ten,Color.Diamond),new Card(Cards.Two,Color.Diamond), new Card(Cards.Queen,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Eight,Color.Diamond))) == "Flush"))

  test("Straight")(assert(check(List(new Card(Cards.Ten,Color.Diamond),new Card(Cards.Nine,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Heart), new Card(Cards.Six,Color.Heart))) == "Straight"))

  test("Three of kind")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Ten,Color.Diamond), new Card(Cards.Ten,Color.Diamond), new Card(Cards.Ace,Color.Heart), new Card(Cards.Eight, Color.Heart))) == "Three of kind"))

  test("Two pair")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Ten,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Eight,Color.Heart), new Card(Cards.Six, Color.Heart))) == "Two pair"))

  test("One pair")(assert(check(List(new Card(Cards.Queen,Color.Club),new Card(Cards.Queen,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Heart), new Card(Cards.Six, Color.Heart))) == "One pair"))

  test("High Card: Ace")(assert(check(List(new Card(Cards.Ace,Color.Club),new Card(Cards.Nine,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: Ace"))

  test("High Card: King")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.King,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: King"))

  test("High Card: Queen")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Four,Color.Diamond), new Card(Cards.Queen,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: Queen"))

  test("High Card: Jack")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Jack,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: Jack"))

  test("High Card: 10")(assert(check(List(new Card(Cards.Ten,Color.Club),new Card(Cards.Two,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: 10"))

  test("High Card: 9")(assert(check(List(new Card(Cards.Three,Color.Club),new Card(Cards.Nine,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: 9"))

  test("High Card: 8")(assert(check(List(new Card(Cards.Three,Color.Club),new Card(Cards.Two,Color.Diamond), new Card(Cards.Eight,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: 8"))

  test("High Card: 7")(assert(check(List(new Card(Cards.Three,Color.Club),new Card(Cards.Two,Color.Diamond), new Card(Cards.Four,Color.Diamond), new Card(Cards.Seven,Color.Diamond), new Card(Cards.Six, Color.Diamond))) == "High Card: 7"))

}
