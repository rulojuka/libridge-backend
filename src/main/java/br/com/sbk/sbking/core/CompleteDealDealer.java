package br.com.sbk.sbking.core;

import java.util.ArrayList;
import java.util.List;

import br.com.sbk.sbking.core.rulesets.abstractClasses.Ruleset;

public class CompleteDealDealer {

	private static final int SIZE_OF_HAND = 13;
	private Direction dealer;

	public CompleteDealDealer(Direction dealer) {
		this.dealer = dealer;
	}

	public Deal deal(Ruleset ruleset) {
		Board board = dealBoard();
		return new Deal(board, ruleset);
	}

	private Board dealBoard() {
		List<Hand> hands = new ArrayList<Hand>();
		ShuffledDeck shuffledDeck = new ShuffledDeck();

		for (Direction direction : Direction.values()) {
			Hand currentHand = new Hand();
			for (int i = 0; i < SIZE_OF_HAND; i++) {
				currentHand.addCard(shuffledDeck.dealCard());
			}
			hands.add(currentHand);
		}

		Board board = new Board(hands.get(0), hands.get(1), hands.get(2), hands.get(3), this.dealer);
		return board;
	}

}