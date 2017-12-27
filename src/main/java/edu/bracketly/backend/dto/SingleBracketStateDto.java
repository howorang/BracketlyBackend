package edu.bracketly.backend.dto;

import lombok.Data;

@Data
public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private int roundCount;
}
