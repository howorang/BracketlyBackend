package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.MatchDto;
import edu.bracketly.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bracket")
public class BracketController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/{bracketId}/play/{matchId}")
    public void playMatch(@PathVariable Long bracketId, @PathVariable Long matchId, @RequestBody Long winningSeatId) {
        matchService.playMatch(bracketId, matchId, winningSeatId);
    }

    @GetMapping("/{bracketId}/match/available")
    public List<MatchDto> getAvailableMatchesForBracket(@PathVariable Long bracketId) {
        return matchService.getCurrentRoundMatchesForBracket(bracketId);
    }

    @GetMapping("{bracketId}/match/next")
    public MatchDto getNextMatchForBracket(@PathVariable Long bracketId) {
        return matchService.getNextMatchForBracket(bracketId);
    }
}
