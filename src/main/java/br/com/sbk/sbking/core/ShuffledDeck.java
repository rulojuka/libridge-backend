package br.com.sbk.sbking.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShuffledDeck {
	private List<Card> deck; // List is best because we need to shuffle it
	private static final int DECK_SIZE = 52;
	private Iterator<Card> iterator;

	public ShuffledDeck() {
		deck = new ArrayList<Card>(DECK_SIZE);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				deck.add(card);
			}
		}
		Collections.shuffle(deck);
		iterator = deck.iterator();
	}

	public Card dealCard() {
		if (iterator.hasNext())
			return iterator.next();
		throw new RuntimeException("Trying to deal card from am empty deck.");
	}

}