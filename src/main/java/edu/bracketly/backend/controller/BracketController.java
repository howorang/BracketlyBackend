package edu.bracketly.backend.controller;

import edu.bracketly.backend.factory.BracketFactory;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.repository.BracketRepository;
import edu.bracketly.backend.service.BracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BracketController {

    @Autowired
    private BracketFactory bracketFactory;

    @Autowired
    private BracketService bracketService;

    @PostMapping("/test")
    public void test() {
        bracketService.createBracket();
    }
}
