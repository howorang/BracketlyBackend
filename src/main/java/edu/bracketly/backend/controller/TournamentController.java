package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.CreateTournamentDto;
import edu.bracketly.backend.dto.EditTournamentDto;
import edu.bracketly.backend.dto.TournamentSimpleDto;
import edu.bracketly.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @PostMapping
    public void createTournament(CreateTournamentDto createTournamentDto) {
        tournamentService.createTournament(createTournamentDto);
    }

    @Autowired
    private TournamentService tournamentService;

    @PutMapping("/join/{tournamentId}")
    public void joinTournament(@PathVariable Long tournamentId) {
        tournamentService.joinTournament(tournamentId);
    }

    @GetMapping("/all")
    public List<TournamentSimpleDto> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping("/start/{tournamentId}")
    public void startTournament(@PathVariable Long tournamentId) {
        tournamentService.startTournament(tournamentId);
    }

    @PutMapping("/{tournamentId}")
    public void editTournament(@PathVariable Long tournamentId, EditTournamentDto dto) {
        tournamentService.modifyTournament(tournamentId, dto);
    }

}