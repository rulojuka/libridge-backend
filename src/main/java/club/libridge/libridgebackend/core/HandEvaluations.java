package club.libridge.libridgebackend.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NonNull;

public final class HandEvaluations {

    @Getter
    private final List<Card> cards;

    public HandEvaluations(@NonNull Hand hand) {
        List<Card> modifiableCards = new ArrayList<Card>();
        for (Card card : hand.getCards()) {
            modifiableCards.add(card);
        }
        this.cards = Collections.unmodifiableList(modifiableCards);
    }

    private List<Card> getCardsPerSuit(@NonNull Suit suit) {
        return this.cards.stream().filter(card -> card.getSuit().equals(suit)).collect(Collectors.toList());
    }

    private Map<Suit, Integer> getNumberOfCardsPerSuit() {
        Map<Suit, Integer> numberOfCards = new EnumMap<Suit, Integer>(Suit.class);
        for (Suit suit : Suit.values()) {
            numberOfCards.put(suit, this.getCardsPerSuit(suit).size());
        }
        return numberOfCards;
    }

    public int getHCP() {
        return this.getCards().stream().map(Card::getPoints).reduce(0, Math::addExact);
    }

    public int getShortestSuitLength() {
        return this.getNumberOfCardsPerSuit().values().stream().reduce(Math::min).orElse(0);
    }

    public int getLongestSuitLength() {
        return this.getNumberOfCardsPerSuit().values().stream().reduce(Math::max).orElse(0);
    }

    public int getNumberOfDoubletonSuits() {
        return (int) this.getNumberOfCardsPerSuit().values().stream().filter(this::isDoubleton).count();
    }

    private boolean isDoubleton(Integer numberOfCards) {
        return numberOfCards == 2;
    }

    public boolean onlyHasHearts() {
        return this.getCards().stream().map(Card::isHeart).reduce(true, Boolean::logicalAnd);
    }

    public boolean hasFiveOrMoreCardsInAMajorSuit() {
        int longestMajor = this.getNumberOfCardsPerSuit().entrySet().stream().filter(this::isMajorSuit).map(Entry::getValue).reduce(0, Math::max);
        return longestMajor >= 5;
    }

    public boolean hasThreeOrMoreCardsInAMinorSuit() {
        int longestMinor = this.getNumberOfCardsPerSuit().entrySet().stream().filter(this::isMinorSuit).map(Entry::getValue).reduce(0, Math::max);
        return longestMinor >= 3;
    }

    private boolean isMajorSuit(Map.Entry<Suit, Integer> entry) {
        Suit suit = entry.getKey();
        return Suit.SPADES.equals(suit) || Suit.HEARTS.equals(suit);
    }

    private boolean isMinorSuit(Map.Entry<Suit, Integer> entry) {
        Suit suit = entry.getKey();
        return Suit.DIAMONDS.equals(suit) || Suit.CLUBS.equals(suit);
    }

    public boolean isBalanced() {
        return this.getShortestSuitLength() >= 2 && this.getLongestSuitLength() <= 5 && this.getNumberOfDoubletonSuits() <= 1;
    }

    public boolean hasEightOrMoreCardsInAnySuit() {
        return hasNOrMoreCardsInLongestSuit(8);
    }

    public boolean hasSixCardsInLongestSuit() {
        return hasNCardsInLongestSuit(6);
    }

    public boolean hasSevenCardsInLongestSuit() {
        return hasNCardsInLongestSuit(7);
    }

    private boolean hasNCardsInLongestSuit(int n) {
        return this.getNumberOfCardsInLongestSuit() == n;
    }

    private boolean hasNOrMoreCardsInLongestSuit(int n) {
        return this.getNumberOfCardsInLongestSuit() >= n;
    }

    public boolean hasTwoOutOfThreeHigherCards(Suit suit) {
        return this.getCardsPerSuit(suit).stream().filter(this::isThreeHigherCards).count() >= 2;
    }

    public boolean hasThreeOutOfFiveHigherCards(Suit suit) {
        return this.getCardsPerSuit(suit).stream().filter(this::isFiveHigherCards).count() >= 3;
    }

    private boolean isThreeHigherCards(Card card) {
        Rank rank = card.getRank();
        return Rank.ACE.equals(rank) || Rank.KING.equals(rank) || Rank.QUEEN.equals(rank);
    }

    private boolean isFiveHigherCards(Card card) {
        Rank rank = card.getRank();
        return Rank.ACE.equals(rank) || Rank.KING.equals(rank) || Rank.QUEEN.equals(rank) || Rank.JACK.equals(rank) || Rank.TEN.equals(rank);
    }

    public Suit getLongestSuit() {
        return Collections.max(this.getNumberOfCardsPerSuit().entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    public Boolean hasFourOrMoreCardsInMajorSuitExcludingLongestSuit() {
        Suit longestSuit = this.getLongestSuit();
        long numberOfMajorSuitsWithFourCardsAndDifferentThanLongestSuit = this.getNumberOfCardsPerSuit().entrySet().stream().filter(entry -> {
            Suit suit = entry.getKey();
            Integer numberOfCards = entry.getValue();
            return !suit.equals(longestSuit) && numberOfCards >= 4 && isMajorSuit(entry);
        }).count();
        return numberOfMajorSuitsWithFourCardsAndDifferentThanLongestSuit != 0;
    }

    private int getNumberOfCardsInLongestSuit() {
        return this.getNumberOfCardsPerSuit().values().stream().reduce(0, Math::max);
    }

    /**
     * @return Return the longest major if they have different lengths. If they have equal lengths, return spades.
     */
    public Suit getLongestMajor() {
        return this.compareSuitLengthWithPriority(Suit.SPADES, Suit.HEARTS);
    }

    /**
     * @return Return the longest minor if they have different lengths. If they have equal lengths, return clubs.
     */
    public Suit getLongestMinor() {
        return this.compareSuitLengthWithPriority(Suit.CLUBS, Suit.DIAMONDS);
    }

    private Suit compareSuitLengthWithPriority(Suit highPrioritySuit, Suit lowPrioritySuit) {
        Map<Suit, Integer> map = this.getNumberOfCardsPerSuit();
        if (map.get(lowPrioritySuit) > map.get(highPrioritySuit)) {
            return lowPrioritySuit;
        } else {
            return highPrioritySuit;
        }
    }

}
