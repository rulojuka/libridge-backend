package club.libridge.libridgebackend.core.rulesets.interfaces;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Trick;
import lombok.NonNull;

public interface Winnable {

    Direction getWinner(@NonNull Trick trick);

}
