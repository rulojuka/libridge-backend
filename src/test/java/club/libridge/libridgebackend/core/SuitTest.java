package club.libridge.libridgebackend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SuitTest {

    private static Suit diamonds;
    private static Suit clubs;
    private static Suit hearts;
    private static Suit spades;

    @BeforeAll
    public static void setup() {
        diamonds = Suit.DIAMONDS;
        clubs = Suit.CLUBS;
        hearts = Suit.HEARTS;
        spades = Suit.SPADES;
    }

    @Test
    public void theSameSuitShouldAlwaysBeTheSameObject() {
        Suit suit1 = Suit.CLUBS;
        Suit suit2 = Suit.CLUBS;
        assertTrue(suit1 == suit2);
    }

    @Test
    public void theSameSuitShouldAlwaysBeEqual() {
        Suit suit1 = Suit.CLUBS;
        Suit suit2 = Suit.CLUBS;
        assertEquals(suit1, suit2);
    }

    @Test
    public void shouldGetName() {
        assertEquals("Diamonds", diamonds.getName());
        assertEquals("Clubs", clubs.getName());
        assertEquals("Hearts", hearts.getName());
        assertEquals("Spades", spades.getName());
    }

    @Test
    public void shouldGetSymbol() {
        assertEquals("d", diamonds.getSymbol());
        assertEquals("c", clubs.getSymbol());
        assertEquals("h", hearts.getSymbol());
        assertEquals("s", spades.getSymbol());
    }

}
