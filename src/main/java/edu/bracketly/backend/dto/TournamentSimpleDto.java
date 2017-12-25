package edu.bracketly.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TournamentSimpleDto {
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date eventDate;
}
