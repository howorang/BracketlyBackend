package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.RoundDto;
import edu.bracketly.backend.service.SingleEliminationBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("single-bracket/")
public class SingleEliminationBracketController {

    @Autowired
    private SingleEliminationBracketService singleEliminationBracketService;

    @GetMapping("/{bracketId}/rounds")
    public List<RoundDto> getAllRounds(@PathVariable Long bracketId) {
        return singleEliminationBracketService.getAllRounds(bracketId);
    }

    @GetMapping("/{bracketId}/round/{roundNumber}")
    public RoundDto getRound(@PathVariable Long bracketId,
                             @PathVariable Integer roundNumber) {
        return singleEliminationBracketService.getRound(bracketId, roundNumber);
    }
}
