package club.libridge.libridgebackend.core.boarddealer;

import java.util.ArrayDeque;
import java.util.Deque;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Rank;
import club.libridge.libridgebackend.core.Suit;
import lombok.Getter;

public class Partial32CardDeck implements CardDeck {

    @Getter
    private final Deque<Card> deck;

    public Partial32CardDeck() {
        this.deck = new ArrayDeque<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                if (rank.compareTo(Rank.SEVEN) >= 0) { // Rank is greater or equal than seven
                    Card card = new Card(suit, rank);
                    this.deck.add(card);
                }
            }
        }
    }

}
