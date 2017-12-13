package edu.bracketly.backend.model.entity.match;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.flow.MATCH_STATUS;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Match extends BaseEntity {
    @ElementCollection
    private Set<Seat> seats;
    private Seat winnerSeat;
    private MATCH_STATUS matchStatus = MATCH_STATUS.NOT_PLAYED;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(seats, match.seats);
    }

    @Override
    public int hashCode() {

        return Objects.hash(seats);
    }
}
