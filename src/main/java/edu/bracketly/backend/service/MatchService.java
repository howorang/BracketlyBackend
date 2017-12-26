package edu.bracketly.backend.service;

import edu.bracketly.backend.dto.MatchDto;
import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.flow.FlowHandler;
import edu.bracketly.backend.repository.BracketRepository;
import edu.bracketly.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private BracketRepository bracketRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RankingService rankingService;

    public List<MatchDto> getCurrentRoundMatchesForBracket(Long bracketId) {
        Bracket bracket = bracketRepository.getOne(bracketId);
        FlowHandler flowHandler = bracket.flowHandler();
        List<Match> matches = flowHandler.getAvailiableMatches();
        return matches.stream().map(MatchDto::asDto).collect(Collectors.toList());
    }

    public MatchDto getNextMatchForBracket(Long bracketId) {
        Bracket bracket = bracketRepository.getOne(bracketId);
        FlowHandler flowHandler = bracket.flowHandler();
        Match match = flowHandler.getNextMatch();
        bracketRepository.save(bracket);
        return MatchDto.asDto(match);
    }

    public void playMatch(Long bracketId, Long matchId, Long winningSeatId) {
        Bracket bracket = bracketRepository.getOne(bracketId);
        FlowHandler flowHandler = bracket.flowHandler();
        flowHandler.markAsPlayed(matchId, winningSeatId);
        updateRankings(matchId, winningSeatId);
        bracketRepository.save(bracket);
    }

    private void updateRankings(Long matchId, Long winningSeatId) {
        Match match = matchRepository.findOne(matchId);
        User winner = match.getSeats().stream()
                .filter(seat -> seat.getId().equals(winningSeatId))
                .map(Seat::getPlayer)
                .findFirst().get();
        User loser = match.getSeats().stream()
                .filter(seat -> !seat.getId().equals(winningSeatId))
                .map(Seat::getPlayer)
                .findFirst().get();
        rankingService.registerWin(winner, loser);
    }

    public void startMatch(Long bracketId, Long matchId) {
        Bracket bracket = bracketRepository.getOne(bracketId);
        FlowHandler flowHandler = bracket.flowHandler();
        flowHandler.startMatch(matchId);
        bracketRepository.save(bracket);
    }
}
