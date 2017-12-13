package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.tree.Node;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"number", "depth"})
public class Seat extends BaseEntity implements Node {
    private Player player;
    @ElementCollection
    private Set<Seat> children;
    private Seat parent;
    private boolean isLeaf;
    private int depth;
    private int number;
}