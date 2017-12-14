package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.CreateTournamentDto;
import edu.bracketly.backend.dto.CreateTournamentResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    public CreateTournamentResponseDto createTournament(CreateTournamentDto createTournamentDto) {
        return null;
    }
}
