package br.com.sbk.sbking.core.comparators;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

import br.com.sbk.sbking.core.Card;
import br.com.sbk.sbking.core.Suit;

public class CardInsideHandWithSuitComparator implements Comparator<Card> {

  private static Map<Suit, Map<Suit, Integer>> suitOrders;

  static {
    Map<Suit, Integer> whenSpades = new EnumMap<Suit, Integer>(Suit.class);
    whenSpades.put(Suit.SPADES, 0);
    whenSpades.put(Suit.HEARTS, 1);
    whenSpades.put(Suit.CLUBS, 2);
    whenSpades.put(Suit.DIAMONDS, 3);

    Map<Suit, Integer> whenHearts = new EnumMap<Suit, Integer>(Suit.class);
    whenHearts.put(Suit.HEARTS, 0);
    whenHearts.put(Suit.SPADES, 1);
    whenHearts.put(Suit.DIAMONDS, 2);
    whenHearts.put(Suit.CLUBS, 3);

    Map<Suit, Integer> whenDiamonds = new EnumMap<Suit, Integer>(Suit.class);
    whenDiamonds.put(Suit.DIAMONDS, 0);
    whenDiamonds.put(Suit.SPADES, 1);
    whenDiamonds.put(Suit.HEARTS, 2);
    whenDiamonds.put(Suit.CLUBS, 3);

    Map<Suit, Integer> whenClubs = new EnumMap<Suit, Integer>(Suit.class);
    whenClubs.put(Suit.CLUBS, 0);
    whenClubs.put(Suit.HEARTS, 1);
    whenClubs.put(Suit.SPADES, 2);
    whenClubs.put(Suit.DIAMONDS, 3);

    suitOrders = new EnumMap<>(Suit.class);
    suitOrders.put(Suit.SPADES, whenSpades);
    suitOrders.put(Suit.HEARTS, whenHearts);
    suitOrders.put(Suit.DIAMONDS, whenDiamonds);
    suitOrders.put(Suit.CLUBS, whenClubs);
  }

  private Map<Suit, Integer> suitOrder;

  public CardInsideHandWithSuitComparator(Suit suit) {
    this.suitOrder = suitOrders.get(suit);
  }

  @Override
  public int compare(Card card1, Card card2) {
    int suitDifference = suitOrder.get(card2.getSuit()) - suitOrder.get(card1.getSuit());
    if (suitDifference != 0) {
      return -suitDifference;
    } else {
      int rankDifference = card1.compareRank(card2);
      return -rankDifference;
    }
  }

}
