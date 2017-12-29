package edu.bracketly.backend.model.entity.user;

import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.service.RankingService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Player extends User {

    public Player(String user, String password) {
        super(user, password);
    }

    private long rank = RankingService.STARTING_RANK;

    @OneToMany
    private List<Tournament> tournaments;

    private int gamesPlayed = 0;

    @Enumerated(EnumType.STRING)
    private K_FACTOR kFactor = K_FACTOR.BEGGINER;
}
