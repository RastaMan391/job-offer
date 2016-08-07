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

  def randomCard = {
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

  def countNumberCards(list: List[Card], x: Int) = {
    list.count(_.number == list(x).number)
  }

  def checkTheSameCard(list: List[Card]): Int = {
    6 - List(list(0).number, list(1).number, list(2).number, list(3).number, list(4).number).distinct.length
  }

  def checkPair(list: List[Card]) = {
    def checkTwoPair(list: List[Card], value: Int): Int = {
      if (list.length - 2 > value) countNumberCards(list, value) + checkTwoPair(list, value + 1)
      else value
    }

    if(checkTheSameCard(list) == 2 ) ResultEnd(result.OnePair, "One pair")
    else if(checkTheSameCard(list) == 3 && checkTwoPair(list, 0) != 9) ResultEnd(result.ThreeOfKind, "Three of kind")
    else if(checkTheSameCard(list) == 4 && (checkFullHouse(list)).number == 0) ResultEnd(result.FourOfKind, "Four of kind")
    else if(checkTheSameCard(list) == 3) ResultEnd(result.TwoPairs, "Two pairs")
    else ResultEnd(result.Nothing, "")
  }

  def checkFullHouse(list: List[Card]): ResultEnd = {
    def countCards(list: List[Card]) = {
      list.count(_.number == (list.distinct(0).number ))
    }

    if(checkTheSameCard(list) == 4 && (countCards(list) == 2 || countCards(list) == 3)) ResultEnd(result.FullHouse, "Full house")
    else ResultEnd(result.Nothing,"")
  }

  def checkStraight(list:List[Card]): ResultEnd = {
    def checkStraightUp(list: List[Card]): Boolean = {
      if (list(0).number == list(1).number + 1 && list.length > 2) checkStraightUp(list.tail)
      else if(list(0).number == list(1).number + 1 && list.length == 2) true
      else false
    }

    def checkStraightDown(list: List[Card]): Boolean = {
      if (list(0).number == list(1).number - 1 && list.length > 2) checkStraightDown(list.tail)
      else if(list(0).number == list(1).number - 1 && list.length == 2) true
      else false
    }

    if(checkStraightUp(list.sortWith(_.number > _.number)) || checkStraightDown(list.sortWith(_.number < _.number))) ResultEnd(result.Straight, "Straight")
    else ResultEnd(result.Nothing, "")
  }

  def checkColor(list: List[Card]): ResultEnd = {
    if(list(0).color == list(1).color && list.length > 2) checkColor(list.tail)
    else if (list(0).color == list(1).color && list.length == 2) ResultEnd(result.Flush, "Flush")
    else ResultEnd(result.Nothing, "")
  }

  def checkPoker(list: List[Card]): ResultEnd = {
    if ((checkColor(list)).number == 13 && (checkStraight(list)).number == 12) ResultEnd(result.StraightFlush, "Straight flush!")
    else ResultEnd(result.Nothing,"")
  }

  def check(list: List[Card]) = {
    if(checkHand(list)) throw new IllegalArgumentException("Incorrect cards")
    else {
      val score = List(checkPoker(list), checkPair(list), checkFullHouse(list), checkColor(list), checkStraight(list)).sortWith(_.number > _.number)(0)
      if (score.number != 0) score.description
      else "High Card: " + matchTest(checkHighCard(list, 0))
    }
  }
}
