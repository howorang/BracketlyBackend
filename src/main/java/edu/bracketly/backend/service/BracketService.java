package edu.bracketly.backend.service;

import edu.bracketly.backend.factory.BracketFactory;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.entity.match.Round;
import edu.bracketly.backend.repository.BracketRepository;
import edu.bracketly.backend.repository.MatchRepository;
import edu.bracketly.backend.repository.RoundRepository;
import edu.bracketly.backend.repository.SeatRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BracketService {

    @Autowired
    private BracketFactory bracketFactory;

    @Autowired
    private BracketRepository bracketRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private SeatRepository seatRepository;

    public void createBracket() {
        SingleEliminationBracket bracket = bracketFactory.singleEliminationBracket(16);
        saveBracket(bracket);
        bracketRepository.save(bracket);
    }

    private void saveBracket(SingleEliminationBracket bracket) {
        seatRepository.save(bracket.getBracketRoot());
        for (int i = 0; i < bracket.getRounds().size(); i++) {
            roundRepository.save(bracket.getRounds().get(i));
        }
    }
}
