package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.flow.MATCH_STATUS;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MatchDto {
    private Long id;
    private String tag;
    private List<SeatDto> players;
    private int round;
    private MATCH_STATUS matchStatus;

    public static MatchDto asDto(Match match) {
        MatchDto dto = new MatchDto();
        dto.setId(match.getId());
        dto.setTag(match.getTag().toString());
        dto.setMatchStatus(match.getMatchStatus());
        dto.setPlayers(match.getSeats().stream().map(SeatDto::asDto).collect(Collectors.toList()));
        //TO DO round
        return dto;
    }
}
