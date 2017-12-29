package edu.bracketly.backend.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import edu.bracketly.backend.dto.MatchDto;
import edu.bracketly.backend.dto.PlayerDetailsDto;
import edu.bracketly.backend.dto.PlayerDto;
import edu.bracketly.backend.dto.TournamentDto;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.entity.match.QMatch;
import edu.bracketly.backend.model.entity.user.Player;
import edu.bracketly.backend.model.flow.MATCH_STATUS;
import edu.bracketly.backend.repository.MatchRepository;
import edu.bracketly.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    public List<PlayerDto> getAllPlayers(String field, Integer page, Integer size, Sort.Direction direction) {
        PageRequest pageRequest = new PageRequest(page, size, direction, field);
        Page<Player> playerPage = playerRepository.findAll(pageRequest);
        return playerPage.getContent().stream().map(PlayerDto::asDto).collect(Collectors.toList());
    }

    public PlayerDetailsDto getPlayerDetails(Long playerId) {
        PlayerDetailsDto dto = new PlayerDetailsDto();
        Player player = playerRepository.findOne(playerId);
        dto.setId(player.getId());
        dto.setUsername(player.getUsername());
        dto.setRank(player.getRank());
        dto.setGamesPlayed(player.getGamesPlayed());
        dto.setTournaments(player.getTournaments().stream().map(TournamentDto::asDto).collect(Collectors.toList()));
        dto.setLiveMatches(getLiveMatches(player));
        return dto;
    }

    private List<MatchDto> getLiveMatches(Player player) {
        BooleanExpression query = QMatch.match.seats.any().player.id.eq(player.getId())
                .and(QMatch.match.matchStatus.eq(MATCH_STATUS.LIVE));
        Iterable<Match> all = matchRepository.findAll(query);
        return StreamSupport.stream(all.spliterator(), false)
                .map(MatchDto::asDto).collect(Collectors.toList());
    }
}
