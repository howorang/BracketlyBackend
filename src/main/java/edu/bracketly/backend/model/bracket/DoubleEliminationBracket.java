package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.utlis.MathUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public class DoubleEliminationBracket extends Bracket {

    private PlayerSlot higherBracketRoot;
    private PlayerSlot lowerBracketRoot;

    private int numberOfPlayers;

    public DoubleEliminationBracket(int numberOfPlayers) {
        super(numberOfPlayers);
        initBracket();
    }

    @Override
    List<PlayerSlot> getStartingPlayerSlotsInPlayingOrder() {
        return null;
    }

    @Override
    int getDistance(PlayerSlot one, PlayerSlot two) {
        return 0;
    }

    private void initBracket() {
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;

       higherBracketRoot = generatePerfectBracket(perfectBracketSize);
       return;
    }
}
