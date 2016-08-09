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

  object Result extends Enumeration {
    type Result = Value
    val Nothing, OnePair, TwoPairs, ThreeOfKind, Straight, Flush, FullHouse, FourOfKind, StraightFlush = Value
  }

  case class ResultEnd(resultSuit: Result.Value, description: String)

  case class Card(valueOfCard: Cards.Value, color: Color.Value)

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
    list.map(_.valueOfCard).sortWith(_ > _)(0)
  }

  def checkTheSameCard(list: List[Card]): Int = {
    list.map(_.valueOfCard).distinct.length
  }

  def checkPair(list: List[Card]): ResultEnd = {
    if(checkTheSameCard(list) == 4 ) ResultEnd(Result.OnePair, "One pair")
    else if(checkTheSameCard(list) == 3 && (list.map(_.valueOfCard)).map(x => list.count(_.valueOfCard == x)).max == 3) ResultEnd(Result.ThreeOfKind, "Three of kind")
    else if(checkTheSameCard(list) == 2 && (checkFullHouse(list)).resultSuit == Result.Nothing) ResultEnd(Result.FourOfKind, "Four of kind")
    else if(checkTheSameCard(list) == 3) ResultEnd(Result.TwoPairs, "Two pairs")
    else ResultEnd(Result.Nothing, "")
  }

  def checkFullHouse(list: List[Card]): ResultEnd = {
    def countDuplicateCards(list: List[Card]) = {
      list.count(_.valueOfCard == (list.distinct(0).valueOfCard ))
    }

    if(checkTheSameCard(list) == 2 && (countDuplicateCards(list) == 2 || countDuplicateCards(list) == 3)) ResultEnd(Result.FullHouse, "Full house")
    else ResultEnd(Result.Nothing,"")
  }

  def checkStraight(list:List[Card]): ResultEnd = {
    if(list.map(_.valueOfCard).zipWithIndex.map(x => (x._1).id + x._2).distinct.length == 1) ResultEnd(Result.Straight, "Straight")
    else ResultEnd(Result.Nothing, "")
  }

  def checkColor(list: List[Card]): ResultEnd = {
    if(list.map(_.color).distinct.length == 1) ResultEnd(Result.Flush, "Flush")
    else ResultEnd(Result.Nothing, "")
  }

  def checkPoker(list: List[Card]): ResultEnd = {
    if ((checkColor(list)).resultSuit == Result.Flush && (checkStraight(list.sortWith(_.valueOfCard > _.valueOfCard))).resultSuit == Result.Straight) ResultEnd(Result.StraightFlush, "Straight flush!")
    else ResultEnd(Result.Nothing,"")
  }

  def lazyCheckResult(score: Stream[ResultEnd]): ResultEnd = {
    if(score.isEmpty) ResultEnd(Result.Nothing, "Nothing")
    else if(score.head.resultSuit == Result.Nothing) lazyCheckResult(score.tail)
    else score.head
  }

  def check(list: List[Card]): String = {
    if(checkHand(list)) throw new IllegalArgumentException("Incorrect cards")
    else {
      val scoreList = List(checkPoker(list), checkPair(list), checkFullHouse(list), checkColor(list), checkStraight(list.sortWith(_.valueOfCard > _.valueOfCard))).toStream
      val score = lazyCheckResult(scoreList)
      if (score.resultSuit != Result.Nothing) score.description
      else "High Card: " + checkHighCard(list).toString
    }
  }
}
