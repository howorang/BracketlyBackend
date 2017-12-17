package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import lombok.Data;

@Data
public abstract class BracketStateDto {
    private PlayerDto winner;
    private BRACKET_STATUS bracket_status;
}
