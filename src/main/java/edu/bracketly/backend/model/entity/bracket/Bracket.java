package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.FlowHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Bracket extends BaseEntity {
    private int numberOfPlayers;

    @Enumerated(EnumType.STRING)
    private BRACKET_STATUS bracketStatus = BRACKET_STATUS.LIVE;

    public abstract List<Seat> getStartingSeatsInPlayingOrder();

    public abstract FlowHandler flowHandler();
}