package edu.bracketly.backend.model.entity.match;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.bracket.Bracket;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Round extends BaseEntity {

    private int roundNumber;

    @ManyToOne
    private Bracket bracket;

    @OneToMany
    private List<Match> matches;
}
