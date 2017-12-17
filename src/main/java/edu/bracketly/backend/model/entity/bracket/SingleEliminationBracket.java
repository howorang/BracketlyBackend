package edu.bracketly.backend.model.entity.bracket;


import edu.bracketly.backend.model.entity.match.Round;
import edu.bracketly.backend.model.flow.FlowHandler;
import edu.bracketly.backend.model.flow.SingleEliminationBracketFlowHandler;
import edu.bracketly.backend.utlis.BracketUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class SingleEliminationBracket extends Bracket {

    private int numberOfRounds;

    @OneToOne(cascade = CascadeType.ALL)
    private Seat bracketRoot;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    private int currentRoundNumber = 1;

    private transient SingleEliminationBracketFlowHandler flowHandler = null;

    @Override
    public List<Seat> getStartingSeatsInPlayingOrder() {
        return BracketUtils.getLeaves(this.getBracketRoot());
    }

    @Override
    public FlowHandler flowHandler() {
        if (flowHandler == null) {
            flowHandler = new SingleEliminationBracketFlowHandler(this);
        }
        return flowHandler;
    }
}
