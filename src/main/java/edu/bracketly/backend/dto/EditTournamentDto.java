package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.BRACKET_TYPE;
import edu.bracketly.backend.model.entity.SEEDING_STRATEGY;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EditTournamentDto {

    String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date eventDate;

    private BRACKET_TYPE bracketType;

    private SEEDING_STRATEGY seedingStrategy;
}
