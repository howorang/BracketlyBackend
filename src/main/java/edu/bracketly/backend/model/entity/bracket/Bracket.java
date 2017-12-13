package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.FlowHandler;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Bracket extends BaseEntity {
    private int numberOfPlayers;
    private int numberOfRounds;
    private Seat bracketRoot;
    private transient FlowHandler flowHandler;
    private int currentRoundNumber = 1;
    private BRACKET_STATUS bracketStatus = BRACKET_STATUS.LIVE;
    public abstract List<Seat> getStartingSeatsInPlayingOrder();
}