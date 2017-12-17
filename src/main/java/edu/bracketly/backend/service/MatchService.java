package edu.bracketly.backend.service;

import edu.bracketly.backend.dto.MatchDto;
import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.flow.FlowHandler;
import edu.bracketly.backend.repository.BracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private BracketRepository bracketRepository;

    public List<MatchDto> getCurrentRoundMatchesForBracket(Long bracketId) {
        Bracket bracket = bracketRepository.getOne(bracketId);
        FlowHandler flowHandler = bracket.flowHandler();
        List<Match> matches = flowHandler.getAvailiableMatches();
        return matches.stream().map(MatchDto::asDto).collect(Collectors.toList());
    }
}
