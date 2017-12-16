package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.BRACKET_TYPE;
import edu.bracketly.backend.model.entity.SEEDING_STRATEGY;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateTournamentDto {
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private Date eventDate;

    @NotNull
    private BRACKET_TYPE bracketType;

    @NotNull
    private SEEDING_STRATEGY seedingStrategy;
}
