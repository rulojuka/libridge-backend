package club.libridge.libridgebackend.pbn;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.HandBuilder;

public final class PBNUtils {

    /**
     * 69 in fact: 52 cards + 3*4 = 12 dots, 3 spaces and a "X:" in the start
     */
    private static final int MAX_CHARS_IN_DEAL_TAG = 70;
    private static final Map<Character, Direction> CHAR_TO_DIRECTION_MAP;

    static {
        Map<Character, Direction> temp = new HashMap<Character, Direction>();
        temp.put('N', Direction.NORTH);
        temp.put('n', Direction.NORTH);
        temp.put('E', Direction.EAST);
        temp.put('e', Direction.EAST);
        temp.put('S', Direction.SOUTH);
        temp.put('s', Direction.SOUTH);
        temp.put('W', Direction.WEST);
        temp.put('w', Direction.WEST);
        CHAR_TO_DIRECTION_MAP = Collections.unmodifiableMap(temp);

    }

    private PBNUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    public static String dealTagStringFromBoard(Board board) {
        return dealTagStringFromBoardAndDirection(board, Direction.NORTH);
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    public static String dealTagStringFromBoardAndDirection(Board board, Direction firstDirection) {
        StringBuilder returnValue = new StringBuilder(MAX_CHARS_IN_DEAL_TAG);

        for (int i = 0; i < 4; i++) {
            Direction current = firstDirection.next(i);
            Hand currentHand = board.getHandOf(current);
            if (i == 0) {
                returnValue.append(firstDirection.getAbbreviation() + ":");
            } else {
                returnValue.append(" ");
            }
            returnValue.append(dealTagPartialStringFromHand(currentHand));
        }
        return returnValue.toString();
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    private static String dealTagPartialStringFromHand(Hand hand) {
        return hand.toString();
    }

    /**
     * Example "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
     */
    public static Board getBoardFromDealTag(String dealTag) {
        HandBuilder handBuilder = new HandBuilder();
        Direction dealer = CHAR_TO_DIRECTION_MAP.get(dealTag.charAt(0));
        String[] dotSeparatedStrings = dealTag.substring(2).split(" ");
        Map<Direction, Hand> hands = new EnumMap<Direction, Hand>(Direction.class);
        for (int i = 0; i < 4; i++) {
            hands.put(dealer.next(i), handBuilder.buildFromDotSeparatedString(dotSeparatedStrings[i]));
        }

        return new Board(hands, dealer);
    }

}
