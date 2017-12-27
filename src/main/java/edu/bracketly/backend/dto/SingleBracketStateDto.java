package edu.bracketly.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private int roundCount;

    public SingleBracketStateDto() {
        super(SingleBracketStateDto.class);

    }
}
