package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.utlis.MathUtils;

import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public class DoubleEliminationBracket extends Bracket {

    private Seat lowerBracketRoot;

    private int numberOfPlayers;

    public DoubleEliminationBracket(int numberOfPlayers) {
        super(numberOfPlayers);
        initBracket();
    }

    @Override
    public List<Seat> getStartingPlayerSlotsInPlayingOrder() {
        return null;
    }

    @Override
    public int getDistance(Seat one, Seat two) {
        return 0;
    }

    private void initBracket() {
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;

       bracketRoot = generatePerfectBracket(perfectBracketSize);
       return;
    }
}
