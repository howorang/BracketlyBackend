package edu.bracketly.backend.model.entity.match;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.bracket.Bracket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Round extends BaseEntity {

    private int roundNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Bracket bracket;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Match> matches;
}
