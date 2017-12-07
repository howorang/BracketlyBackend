package edu.bracketly.backend.model;

import edu.bracketly.backend.utlis.MathUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public class DoubleEliminationBracket extends Bracket {

    private Match higherBracketRoot;
    private Match lowerBracketRoot;

    private int numberOfPlayers;

    protected DoubleEliminationBracket(List<Player> competitors) {
        super(competitors);
        numberOfPlayers = competitors.size();
        initBracket();
    }

    private void initBracket() {
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;

       higherBracketRoot = generatePerfectBracket(perfectBracketSize);
       return;
    }
}
