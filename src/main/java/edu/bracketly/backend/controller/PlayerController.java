package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.PlayerDetailsDto;
import edu.bracketly.backend.dto.PlayerDto;
import edu.bracketly.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/all")
    public List<PlayerDto> getAllPlayers(@RequestParam(defaultValue = "0", required = false) Integer page,
                                         @RequestParam(defaultValue = "rank", required = false) String field,
                                         @RequestParam(defaultValue = "10", required = false) Integer size,
                                         @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction) {
        return playerService.getAllPlayers(field, page, size, direction);
    }

    @GetMapping("/{playerId}/details")
    public PlayerDetailsDto getPlayerDetails(@PathVariable Long playerId) {
        return playerService.getPlayerDetails(playerId);
    }
}
