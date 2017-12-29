package edu.bracketly.backend.model.entity;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.user.Player;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.flow.TOURNAMENT_STATUS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Tournament extends BaseEntity {

    String name;

    @ManyToMany
    private Set<Player> players;

    @ManyToOne
    private User organizer;

    @OneToOne
    private Bracket bracket;

    private Date creationDate;

    private Date eventDate;

    @Enumerated(EnumType.STRING)
    private TOURNAMENT_STATUS status;

    @Enumerated(EnumType.STRING)
    private BRACKET_TYPE bracketType;

    @Enumerated(EnumType.STRING)
    private SEEDING_STRATEGY seeding_strategy;

}
