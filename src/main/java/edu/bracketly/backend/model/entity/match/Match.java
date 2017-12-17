package edu.bracketly.backend.model.entity.match;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.flow.MATCH_STATUS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Match extends BaseEntity {
    private Long tag;

    @OneToMany
    private List<Seat> seats;

    @OneToOne
    private Seat winnerSeat;

    @Enumerated(EnumType.STRING)
    private MATCH_STATUS matchStatus = MATCH_STATUS.NOT_PLAYED;
}
