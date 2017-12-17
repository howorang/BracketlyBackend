package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.MatchDto;
import edu.bracketly.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{bracketId}")
    public List<MatchDto> getAvailableMatchesForBracket(@PathVariable Long bracketId) {
        return matchService.getCurrentRoundMatchesForBracket(bracketId);
    }
}
