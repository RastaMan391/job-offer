import scala.util.Random

object Poker {
  object Color extends Enumeration {
    val Club = 1
    val Diamond = 2
    val Heart = 3
    val Spade = 4
  }

  object Cards extends Enumeration {
    val Two = 2
    val Three = 3
    val Four = 4
    val Five = 5
    val Six = 6
    val Seven = 7
    val Eight = 8
    val Nine = 9
    val Ten = 10
    val Jack = 11
    val Queen = 12
    val King = 13
    val Ace = 14
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

  def matchTest(numberOfCard: Int): String = {
    numberOfCard match {
      case 14 => "Ace"
      case 13 => "King"
      case 12 => "Queen"
      case 11 => "Jack"
      case _ => numberOfCard.toString
    }
  }

  case class Card(number: Int, color: Int)

  def checkHand(list: List[Card]): Boolean = {
    list.distinct.length != 5
  }

  def randomCard: Card = {
    val x = new Random
    new Card(x.nextInt(13)+2, x.nextInt(4)+1)
  }

  def randomCards: List[Card] = {
    val cards = List.fill(5)(randomCard)
    if(checkHand(cards)) randomCards
    else cards
  }

  def checkHighCard(list: List[Card], highCard: Int): Int = {
    list.sortWith(_.number > _.number)(0).number
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
    if(list.map(_.number).zipWithIndex.map(x => x._1 + x._2).distinct.length == 1) ResultEnd(result.Straight, "Straight")
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
      else "High Card: " + matchTest(checkHighCard(list, 0))
    }
  }
}
