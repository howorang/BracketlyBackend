package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.match.Round;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoundDto {
    private int number;
    private List<MatchDto> matches;

    public static RoundDto asDto(Round round) {
        RoundDto dto = new RoundDto();
        dto.setNumber(round.getRoundNumber());
        dto.setMatches(round.getMatches().stream().map(MatchDto::asDto).collect(Collectors.toList()));
        return dto;
    }
}
