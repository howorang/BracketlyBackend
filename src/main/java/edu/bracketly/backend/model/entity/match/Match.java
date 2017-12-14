package edu.bracketly.backend.model.entity.match;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.flow.MATCH_STATUS;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Match extends BaseEntity {
    private Long tag;

    @OneToMany
    private List<Seat> seats;

    private Seat winnerSeat;

    private MATCH_STATUS matchStatus = MATCH_STATUS.NOT_PLAYED;
}
