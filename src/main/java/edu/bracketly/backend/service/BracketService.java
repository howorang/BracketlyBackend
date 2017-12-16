package edu.bracketly.backend.service;

import edu.bracketly.backend.factory.BracketFactory;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.seeder.BestWorstSeedingStrategy;
import edu.bracketly.backend.model.seeder.RandomSeedingStrategy;
import edu.bracketly.backend.repository.BracketRepository;
import edu.bracketly.backend.repository.MatchRepository;
import edu.bracketly.backend.repository.RoundRepository;
import edu.bracketly.backend.repository.SeatRepository;
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


    private void saveBracket(SingleEliminationBracket bracket) {
        seatRepository.save(bracket.getBracketRoot());
        for (int i = 0; i < bracket.getRounds().size(); i++) {
            roundRepository.save(bracket.getRounds().get(i));
        }
        bracketRepository.save(bracket);
    }

    public Bracket initBracket(Tournament tournament) {
        Bracket bracket;
        switch (tournament.getBracketType()) {
            case SINGLE_ELIMINATION:
                SingleEliminationBracket singleEliminationBracket = bracketFactory.singleEliminationBracket(tournament.getPlayers().size());
                saveBracket(singleEliminationBracket);
                bracket = singleEliminationBracket;
                break;
            default:
                bracket = null;
                break;
        }
        return bracket;
    }

    public void seedBracket(Tournament tournament) {
        switch (tournament.getSeeding_strategy()) {
            case BEST_WORST:
                new BestWorstSeedingStrategy().seed(tournament.getBracket(), tournament.getPlayers());
            case RANDOM:
                new RandomSeedingStrategy().seed(tournament.getBracket(), tournament.getPlayers());
        }
    }
}
