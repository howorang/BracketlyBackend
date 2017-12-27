package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BracketStateDto {
    private String type;
    private PlayerDto winner;
    private BRACKET_STATUS bracket_status;

    public BracketStateDto(Class<? extends BracketStateDto> type) {
        this.type = type.getSimpleName();
    }
}
