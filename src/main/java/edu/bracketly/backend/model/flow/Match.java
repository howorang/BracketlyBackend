package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.bracket.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Match {
    private long id;
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
