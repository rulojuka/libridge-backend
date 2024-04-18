package club.libridge.libridgebackend.core.rulesets.implementations;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Trick;
import club.libridge.libridgebackend.core.rulesets.interfaces.Winnable;

public class NoTrumpSuitWinnable implements Winnable {

    @Override
    public Direction getWinner(Trick trick) {
        return trick.getWinnerWithoutTrumpSuit();
    }

}
