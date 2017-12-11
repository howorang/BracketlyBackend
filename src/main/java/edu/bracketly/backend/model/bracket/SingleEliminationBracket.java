package edu.bracketly.backend.model.bracket;


import edu.bracketly.backend.utlis.BracketUtils;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class SingleEliminationBracket extends Bracket {
    @Override
    public List<Seat> getStartingSeatsInPlayingOrder() {
        return BracketUtils.getLeaves(this.getBracketRoot());
    }
}
