package edu.bracketly.backend.model;

import edu.bracketly.backend.utlis.MathUtils;

import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public class DoubleEliminationBracket extends Bracket {

    private PlayerSlot higherBracketRoot;
    private PlayerSlot lowerBracketRoot;

    private int numberOfPlayers;

    protected DoubleEliminationBracket(int numberOfPlayers) {
        super(numberOfPlayers);
        initBracket();
    }

    private void initBracket() {
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;

       higherBracketRoot = generatePerfectBracket(perfectBracketSize);
       return;
    }
}
