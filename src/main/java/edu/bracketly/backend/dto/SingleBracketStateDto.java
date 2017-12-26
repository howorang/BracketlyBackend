package edu.bracketly.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private List<RoundDto> rounds;
}
