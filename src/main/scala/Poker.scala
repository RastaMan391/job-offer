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

    if(checkTheSameCard(list) == 2 ) "One pair"
    else if(checkTheSameCard(list) == 3 && checkTwoPair(list, 0) != 9) "Three of kind"
    else if(checkTheSameCard(list) == 4 && checkFullHouse(list) == false) "Four of kind"
    else if(checkTheSameCard(list) == 3) "Two pairs"
    else "Nothing"
  }

  def checkFullHouse(list: List[Card]): Boolean = {
    def countCards(list: List[Card]) = {
      list.count(_.number == (list.distinct(0).number ))
    }

    checkTheSameCard(list) == 4 && (countCards(list) == 3 || countCards(list) == 2)
  }

  def checkStraight(list:List[Card]): Boolean = {
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

    checkStraightUp(list.sortWith(_.number > _.number)) || checkStraightDown(list.sortWith(_.number < _.number))
  }

  def checkColor(list: List[Card]): Boolean = {
    if(list(0).color == list(1).color && list.length > 2) checkColor(list.tail)
    else if (list(0).color == list(1).color && list.length == 2) true
    else false
  }

  def checkPoker(list: List[Card]): Boolean = {
    checkColor(list) && checkStraight(list)
  }

  def check(list: List[Card]): String = {
    if(checkHand(list)) throw new IllegalArgumentException("Incorrect cards")
    else if(checkPoker(list)) "Straight flush!"
    else if(checkPair(list) == "Four of kind") "Four of kind"
    else if(checkFullHouse(list)) "Full house"
    else if(checkColor(list)) "Flush"
    else if(checkStraight(list)) "Straight"
    else if(checkPair(list) == "Three of kind") "Three of kind"
    else if(checkPair(list) == "Two pairs") "Two pairs"
    else if(checkPair(list) == "One pair") "One pair"
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
