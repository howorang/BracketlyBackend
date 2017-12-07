package edu.bracketly.backend.model;

import lombok.Data;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
public class Player {
    private Long id;
    private String name;
    private boolean hasWon;

    public Player(Long id, String name, boolean hasWon) {
        this.id = id;
        this.name = name;
        this.hasWon = hasWon;
    }
}
