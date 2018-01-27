package edu.bracketly.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.model.flow.TOURNAMENT_STATUS;
import lombok.Data;

import java.util.Date;

@Data
public class TournamentSimpleDto {
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date eventDate;

    private Long organizerId;

    private TOURNAMENT_STATUS tournamentStatus;

    public static TournamentSimpleDto asDto(Tournament tournament) {
        TournamentSimpleDto dto = new TournamentSimpleDto();
        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setCreationDate(tournament.getCreationDate());
        dto.setEventDate(tournament.getEventDate());
        dto.setTournamentStatus(tournament.getStatus());
        return dto;
    }
}
