package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.match.Round;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.FlowHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
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

    private int numberOfRounds;

    @OneToOne(cascade = CascadeType.ALL)
    private Seat bracketRoot;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    private int currentRoundNumber = 1;

    @Enumerated(EnumType.STRING)
    private BRACKET_STATUS bracketStatus = BRACKET_STATUS.LIVE;

    private transient FlowHandler flowHandler;

    public abstract List<Seat> getStartingSeatsInPlayingOrder();
}