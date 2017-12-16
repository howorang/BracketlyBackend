package edu.bracketly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TournamentSimpleDto {
    private Long id;
    private String name;
    private Date creationDate;
    private Date eventDate;
}
