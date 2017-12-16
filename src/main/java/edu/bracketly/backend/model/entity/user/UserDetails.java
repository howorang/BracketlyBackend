package edu.bracketly.backend.model.entity.user;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.Tournament;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserDetails extends BaseEntity {
    private long rank;

    @OneToMany
    private List<Tournament> tournaments;
}
