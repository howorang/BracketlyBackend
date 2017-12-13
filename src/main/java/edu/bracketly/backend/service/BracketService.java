package edu.bracketly.backend.service;

import edu.bracketly.backend.factory.BracketFactory;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.repository.BracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BracketService {

    @Autowired
    private BracketFactory bracketFactory;

    @Autowired
    private BracketRepository bracketRepository;

    public void createBracket() {
        SingleEliminationBracket bracket = bracketFactory.singleEliminationBracket(16);
        bracketRepository.save(bracket);
    }
}
