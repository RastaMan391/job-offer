import scala.util.Random

object Poker {
  object Color extends Enumeration {
    type Color = Value
    val Club, Diamond, Heart, Spade = Value
  }

  object Cards extends Enumeration {
    type Cards = Value
    val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace = Value
  }

  object result extends Enumeration {
    val StraightFlush = 16
    val FourOfKind = 15
    val FullHouse = 14
    val Flush = 13
    val Straight = 12
    val ThreeOfKind = 11
    val TwoPairs = 10
    val OnePair = 9
    val Nothing = 0
  }

  case class ResultEnd(number: Int, description: String)

  def matchTest(numberOfCard: Cards.Value): String = {
    numberOfCard match {
      case Cards.Ace => "Ace"
      case Cards.King => "King"
      case Cards.Queen => "Queen"
      case Cards.Jack => "Jack"
      case _ => numberOfCard.toString
    }
  }

  case class Card(number: Cards.Value, color: Color.Value)

  def checkHand(list: List[Card]): Boolean = {
    list.distinct.length != 5
  }

  def randomCard: Card = {
    val x = new Random
    new Card(Cards(x.nextInt(13)+2), Color(x.nextInt(4)+1))
  }

  def randomCards: List[Card] = {
    val cards = List.fill(5)(randomCard)
    if(checkHand(cards)) randomCards
    else cards
  }

  def checkHighCard(list: List[Card]): Cards.Value = {
    list.map(_.number).sortWith(_ > _)(0)
  }

  def checkTheSameCard(list: List[Card]): Int = {
    list.map(_.number).distinct.length
  }

  def checkPair(list: List[Card]): ResultEnd = {
    if(checkTheSameCard(list) == 4 ) ResultEnd(result.OnePair, "One pair")
    else if(checkTheSameCard(list) == 3 && (list.map(_.number)).map(x => list.count(_.number == x)).max == 3) ResultEnd(result.ThreeOfKind, "Three of kind")
    else if(checkTheSameCard(list) == 2 && (checkFullHouse(list)).number == 0) ResultEnd(result.FourOfKind, "Four of kind")
    else if(checkTheSameCard(list) == 3) ResultEnd(result.TwoPairs, "Two pairs")
    else ResultEnd(result.Nothing, "")
  }

  def checkFullHouse(list: List[Card]): ResultEnd = {
    def countCards(list: List[Card]) = {
      list.count(_.number == (list.distinct(0).number ))
    }

    if(checkTheSameCard(list) == 2 && (countCards(list) == 2 || countCards(list) == 3)) ResultEnd(result.FullHouse, "Full house")
    else ResultEnd(result.Nothing,"")
  }

  def checkStraight(list:List[Card]): ResultEnd = {
    if(list.map(_.number).zipWithIndex.map(x => (x._1).id + x._2).distinct.length == 1) ResultEnd(result.Straight, "Straight")
    else ResultEnd(result.Nothing, "")
  }

  def checkColor(list: List[Card]): ResultEnd = {
    if(list.map(_.color).distinct.length == 1) ResultEnd(result.Flush, "Flush")
    else ResultEnd(result.Nothing, "")
  }

  def checkPoker(list: List[Card]): ResultEnd = {
    if ((checkColor(list)).number == 13 && (checkStraight(list.sortWith(_.number > _.number))).number == 12) ResultEnd(result.StraightFlush, "Straight flush!")
    else ResultEnd(result.Nothing,"")
  }

  def check(list: List[Card]): String = {
    if(checkHand(list)) throw new IllegalArgumentException("Incorrect cards")
    else {
      val score = List(checkPoker(list), checkPair(list), checkFullHouse(list), checkColor(list), checkStraight(list.sortWith(_.number > _.number))).sortWith(_.number > _.number)(0)
      if (score.number != 0) score.description
      else "High Card: " + matchTest(checkHighCard(list))
    }
  }
}
