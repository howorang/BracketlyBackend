package edu.bracketly.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.bracketly.backend.model.entity.BRACKET_TYPE;
import edu.bracketly.backend.model.entity.SEEDING_STRATEGY;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.model.flow.TOURNAMENT_STATUS;
import lombok.Data;

import java.util.Date;

@Data
public class TournamentDto {

    private Long id;
    private String name;
    private Long organizerId;
    private BRACKET_TYPE bracketType;
    private SEEDING_STRATEGY seedingStrategy;
    private TOURNAMENT_STATUS tournamentStatus;
    private Long bracketId;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private Date eventDate;

    public static TournamentDto asDto(Tournament tournament) {
        TournamentDto dto = new TournamentDto();
        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setOrganizerId(tournament.getOrganizer().getId());
        if (tournament.getBracketType() != null) dto.setBracketType(tournament.getBracketType());
        if (tournament.getSeeding_strategy() != null) dto.setSeedingStrategy(tournament.getSeeding_strategy());
        dto.setTournamentStatus(tournament.getStatus());
        if (tournament.getBracket() != null) dto.setBracketId(tournament.getBracket().getId());
        dto.setCreationDate(tournament.getCreationDate());
        if (tournament.getEventDate() != null) dto.setEventDate(tournament.getEventDate());
        return dto;
    }
}
