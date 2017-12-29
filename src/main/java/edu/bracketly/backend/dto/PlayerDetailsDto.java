package edu.bracketly.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerDetailsDto extends PlayerDto {
    List<TournamentDto> tournaments;
    List<MatchDto> liveMatches;

    public PlayerDetailsDto() {
        type = PlayerDetailsDto.class.getSimpleName();
    }
}
