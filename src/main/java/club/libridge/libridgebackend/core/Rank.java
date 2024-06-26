package club.libridge.libridgebackend.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank {
    TWO("Two", "2"), THREE("Three", "3"), FOUR("Four", "4"), FIVE("Five", "5"), SIX("Six", "6"), SEVEN("Seven", "7"), EIGHT("Eight", "8"),
    NINE("Nine", "9"), TEN("Ten", "T"), JACK("Jack", "J"), QUEEN("Queen", "Q"), KING("King", "K"), ACE("Ace", "A");

    private final String name;
    private final String symbol;

    public String getSymbol() { // FIXME This is not lomboked because scala cannot cope with it very well
        return this.symbol;
    }

}
