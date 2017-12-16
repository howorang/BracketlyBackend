package edu.bracketly.backend.model.entity;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Tournament extends BaseEntity {

    String name;

    @ManyToMany
    private Set<User> players;

    @ManyToOne
    private User organizer;

    @OneToOne
    private Bracket bracket;
}
