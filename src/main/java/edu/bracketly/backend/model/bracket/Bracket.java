package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.FlowHandler;
import lombok.Data;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
public abstract class Bracket {
    private int numberOfPlayers;
    private int numberOfRounds;
    private Seat bracketRoot;
    private transient FlowHandler flowHandler;
    private int currentRoundNumber = 1;
    private BRACKET_STATUS bracketStatus = BRACKET_STATUS.LIVE;
    public abstract List<Seat> getStartingSeatsInPlayingOrder();
}