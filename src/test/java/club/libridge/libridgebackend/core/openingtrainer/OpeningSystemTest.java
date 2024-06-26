package club.libridge.libridgebackend.core.openingtrainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.app.persistence.BoardFactory;
import club.libridge.libridgebackend.core.Call;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.HandBuilder;
import club.libridge.libridgebackend.core.PavlicekNumber;

public class OpeningSystemTest {

    private static BoardFactory boardFactory;
    private static OpeningSystem subject;
    private static HandBuilder handBuilder;

    @BeforeAll
    public static void setup() {
        boardFactory = new BoardFactory(new PavlicekNumber());
        subject = new OpeningSystem();
        handBuilder = new HandBuilder();
    }

    @Test
    public void getCall_shouldReturnTheRightCall() {
        String pbnString = "972.4.986.AQT842";
        String expectedCall = "P";
        Hand hand = handBuilder.buildFromDotSeparatedString(pbnString);
        Call call = subject.getCall(boardFactory.fromHandAndDirection(hand, Direction.NORTH));
        assertEquals(expectedCall, call.toString());
    }
}
