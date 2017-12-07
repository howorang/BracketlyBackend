package edu.bracketly.backend.model;

import edu.bracketly.backend.utlis.MathUtils;

import java.util.List;

public class SingleEliminationBracket extends Bracket {
    protected SingleEliminationBracket(List<Player> competitors) {
        super(competitors);
        int numberOfPlayers = competitors.size();
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;

    }
}
