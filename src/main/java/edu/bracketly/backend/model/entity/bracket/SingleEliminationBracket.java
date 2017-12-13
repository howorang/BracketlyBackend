package edu.bracketly.backend.model.entity.bracket;


import edu.bracketly.backend.utlis.BracketUtils;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class SingleEliminationBracket extends Bracket {
    @Override
    public List<Seat> getStartingSeatsInPlayingOrder() {
        return BracketUtils.getLeaves(this.getBracketRoot());
    }
}
