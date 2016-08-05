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

  case class Card(number: Int, color: Int)

  def checkHand(list: List[Card]): Boolean = {
    if (list.count(_ == list(0)) > 1) true
    else if (list.length == 1 ) false
    else checkHand(list.tail)
  }

  def randomCard = {
    val x = new Random
    new Card(x.nextInt(13)+2, x.nextInt(4)+1)
  }

  def randomCards: List[Card] = {
    val cards = List(randomCard, randomCard, randomCard, randomCard, randomCard)
    if(checkHand(cards)) randomCards
    else cards
  }

  def checkHighCard(list: List[Card], highCard: Int): Int = {
    if(list(0).number > highCard && list.length > 1) checkHighCard(list.tail, list(0).number)
    else if(list.length > 1) checkHighCard(list.tail, highCard)
    else if(list(0).number > highCard) list(0).number
    else highCard
  }

  def countNumberCards(list: List[Card], x: Int) = {
    list.count(_.number == list(x).number)
  }

  def checkPair(list: List[Card], countCard: Int): Int = {
    if(countNumberCards(list, 0) > countCard && list.length > 1) checkPair(list.tail, countNumberCards(list, 0))
    else if(list.length > 1) checkPair(list.tail, countCard)
    else if(countNumberCards(list, 0) > countCard) countNumberCards(list, 0)
    else countCard
  }

  def checkTwoPair(list: List[Card], value: Int): Int = {
    if (list.length - 2 > value) countNumberCards(list, value) + checkTwoPair(list, value + 1)
    else value
  }

  def checkStraight(list:List[Card]): Boolean = {
    checkStraightUp(list.sortWith(_.number > _.number)) || checkStraightDown(list.sortWith(_.number < _.number))
  }

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


  def checkColor(list: List[Card]): Boolean = {
    if(list(0).color == list(1).color && list.length > 2) checkColor(list.tail)
    else if (list(0).color == list(1).color && list.length == 2) true
    else false
  }

  def checkFullHouse(list: List[Card]): Boolean = {
    val cardCount = List(countNumberCards(list,0),countNumberCards(list,1),countNumberCards(list,2),countNumberCards(list,3),countNumberCards(list,4))
    cardCount.max == 3 && cardCount.count(_ == 2) == 2
  }

  def checkPoker(list: List[Card]): Boolean = {
    checkColor(list) && checkStraight(list)
  }

  def check(list: List[Card]): String = {
    if(checkHand(list) == true) throw new IllegalArgumentException("Incorrect cards")
    else if(checkPoker(list)) "Straight flush!"
    else if(checkPair(list, 0) == 4) "Four of kind"
    else if(checkFullHouse(list)) "Full house"
    else if(checkColor(list)) "Flush"
    else if(checkStraight(list)) "Straight"
    else if(checkPair(list, 0) == 3) "Three of kind"
    else if(checkTwoPair(list, 0) == 9) "Two pair"
    else if(checkPair(list, 0) == 2) "One pair"
    else {
      if(checkHighCard(list,0) == 14) "High Card: Ace"
      else if(checkHighCard(list,0) == 13) "High Card: King"
      else if(checkHighCard(list,0) == 12) "High Card: Queen"
      else if(checkHighCard(list,0) == 11) "High Card: Jack"
      else "High Card: " + checkHighCard(list,0).toString()
    }
  }

  def main(args: Array[String]): Unit ={
    val card: List[Card] = randomCards
    val card2: List[Card] = List(new Card(Cards.Ace,Color.Club),new Card(Cards.Ace,Color.Heart), new Card(Cards.Ten,Color.Heart), new Card(Cards.Seven,Color.Spade), new Card(Cards.Six, Color.Club))

    println(check(card))
    println(check(card2))
  }

}
