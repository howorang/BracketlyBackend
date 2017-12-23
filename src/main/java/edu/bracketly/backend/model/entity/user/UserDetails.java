package edu.bracketly.backend.model.entity.user;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.service.RankingService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserDetails extends BaseEntity {
    private long rank = RankingService.STARTING_RANK;

    @OneToMany
    private List<Tournament> tournaments;

    private int gamesPlayed = 0;

    @Enumerated(EnumType.STRING)
    private K_FACTOR kFactor = K_FACTOR.BEGGINER;
}
