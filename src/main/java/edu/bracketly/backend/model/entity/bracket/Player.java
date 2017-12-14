package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends BaseEntity{
    private Long id;
    private String name;
    private long rank;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
