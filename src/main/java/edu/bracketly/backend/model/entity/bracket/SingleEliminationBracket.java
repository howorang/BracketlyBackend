package edu.bracketly.backend.model.entity.bracket;


import edu.bracketly.backend.dto.BracketStateDto;
import edu.bracketly.backend.dto.PlayerDto;
import edu.bracketly.backend.dto.SingleBracketStateDto;
import edu.bracketly.backend.model.entity.match.Round;
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
    public SingleEliminationBracketFlowHandler flowHandler() {
        if (flowHandler == null) {
            flowHandler = new SingleEliminationBracketFlowHandler(this);
        }
        return flowHandler;
    }

    @Override
    public BracketStateDto getStateDto() {
        SingleBracketStateDto dto = new SingleBracketStateDto();
        dto.setBracket_status(bracketStatus);
        dto.setCurrentRound(currentRoundNumber);
        if (bracketRoot.getPlayer() != null) {
            dto.setWinner(PlayerDto.asDto(bracketRoot.getPlayer()));
        }
        return dto;
    }
}
