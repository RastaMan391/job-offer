import org.scalatest.FunSuite
import Poker._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PokerTest extends FunSuite {
  test("Straight flush!")(assert(check(List (Card(Cards.Ten,Color.Diamond), Card(Cards.Nine,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond), Card(Cards.Eight,Color.Diamond))) == "Straight flush!"))

  test("Four of kind")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Ten,Color.Diamond), Card(Cards.Ten,Color.Heart), Card(Cards.Ten,Color.Spade), Card(Cards.Six,Color.Heart))) == "Four of kind"))

  test("Full house")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Ten,Color.Diamond), Card(Cards.Ten,Color.Heart), Card(Cards.Eight,Color.Spade), Card(Cards.Eight,Color.Heart))) == "Full house"))

  test("Flush")(assert(check(List (Card(Cards.Ten,Color.Diamond), Card(Cards.Two,Color.Diamond), Card(Cards.Queen,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Eight,Color.Diamond))) == "Flush"))

  test("Straight")(assert(check(List (Card(Cards.Nine,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Ten,Color.Diamond), Card(Cards.Seven,Color.Heart), Card(Cards.Six,Color.Heart))) == "Straight"))

  test("Three of kind")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Ten,Color.Diamond), Card(Cards.Ten,Color.Heart), Card(Cards.Ace,Color.Heart), Card(Cards.Eight, Color.Heart))) == "Three of kind"))

  test("Two pairs")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Ten,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Eight,Color.Heart), Card(Cards.Six, Color.Heart))) == "Two pairs"))

  test("One pair")(assert(check(List (Card(Cards.Queen,Color.Club), Card(Cards.Queen,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Heart), Card(Cards.Six, Color.Heart))) == "One pair"))

  test("High Card: Ace")(assert(check(List (Card(Cards.Ace,Color.Club), Card(Cards.Nine,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Ace"))

  test("High Card: King")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.King,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: King"))

  test("High Card: Queen")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Four,Color.Diamond), Card(Cards.Queen,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Queen"))

  test("High Card: Jack")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Jack,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Jack"))

  test("High Card: Ten")(assert(check(List (Card(Cards.Ten,Color.Club), Card(Cards.Two,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Ten"))

  test("High Card: Nine")(assert(check(List (Card(Cards.Three,Color.Club), Card(Cards.Nine,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Nine"))

  test("High Card: Eight")(assert(check(List (Card(Cards.Three,Color.Club), Card(Cards.Two,Color.Diamond), Card(Cards.Eight,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Eight"))

  test("High Card: Seven")(assert(check(List (Card(Cards.Three,Color.Club), Card(Cards.Two,Color.Diamond), Card(Cards.Four,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond))) == "High Card: Seven"))

  test("Incorrect cards")(assert(intercept[IllegalArgumentException]{check(List (Card(Cards.Two,Color.Diamond), Card(Cards.Two,Color.Diamond), Card(Cards.Four,Color.Diamond), Card(Cards.Seven,Color.Diamond), Card(Cards.Six, Color.Diamond)))}.getMessage === "Incorrect cards"))
}
