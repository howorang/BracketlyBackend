package edu.bracketly.backend.model.bracket;

import lombok.Data;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
public class Player {
    private Long id;
    private String name;
    private long rank;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
