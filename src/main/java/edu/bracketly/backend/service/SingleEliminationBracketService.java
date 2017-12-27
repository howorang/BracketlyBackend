package edu.bracketly.backend.service;

import edu.bracketly.backend.dto.RoundDto;
import edu.bracketly.backend.exception.BracketDoesNotExistsException;
import edu.bracketly.backend.exception.RoundDoesNotExistException;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.entity.match.Round;
import edu.bracketly.backend.repository.SingleEliminationBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleEliminationBracketService {

    @Autowired
    private SingleEliminationBracketRepository bracketRepository;

    public List<RoundDto> getAllRounds(Long bracketId) {
        checkIfBracketExists(bracketId);
        SingleEliminationBracket bracket = bracketRepository.findOne(bracketId);
        return bracket.getRounds().stream().map(RoundDto::asDto).collect(Collectors.toList());
    }

    public RoundDto getRound(Long bracketId, Integer roundNumber) {
        checkIfBracketExists(bracketId);
        SingleEliminationBracket bracket = bracketRepository.findOne(bracketId);
        if (roundNumber > bracket.getRounds().size()) {
            throw new RoundDoesNotExistException("Round number " + roundNumber
                    + " of bracket with id: " + bracketId + " does not exists");
        }
        Round round = bracket.getRounds().get(roundNumber - 1);
        return RoundDto.asDto(round);
    }

    private void checkIfBracketExists(Long bracketId) {
        if (!bracketRepository.exists(bracketId)) {
            throw new BracketDoesNotExistsException("Bracket with id: " + bracketId + " does not exist");
        }
    }
}
