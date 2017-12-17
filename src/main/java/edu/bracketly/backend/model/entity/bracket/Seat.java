package edu.bracketly.backend.model.entity.bracket;

import edu.bracketly.backend.model.entity.BaseEntity;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.tree.Node;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"children"})
@Entity
public class Seat extends BaseEntity implements Node {

    @OneToOne
    private User player;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seat> children;

    @OneToOne(cascade = CascadeType.ALL)
    private Seat parent;

    private boolean isLeaf;

    private int depth;

    private int number;
}