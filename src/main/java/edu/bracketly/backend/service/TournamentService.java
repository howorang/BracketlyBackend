package edu.bracketly.backend.service;

import edu.bracketly.backend.dto.*;
import edu.bracketly.backend.exception.NoPlayersException;
import edu.bracketly.backend.exception.TournamentDoesNotExistException;
import edu.bracketly.backend.exception.TournamentHasAlreadyBeenStartedException;
import edu.bracketly.backend.exception.WrongUserException;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.flow.TOURNAMENT_STATUS;
import edu.bracketly.backend.repository.TournamentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BracketService bracketService;

    public void createTournament(CreateTournamentDto dto) {
        Tournament newTournament = new Tournament();
        newTournament.setName(dto.getName());
        newTournament.setEventDate(dto.getEventDate());
        newTournament.setCreationDate(new Date());
        newTournament.setOrganizer(userService.getCurrentUser());
        newTournament.setPlayers(Collections.emptySet());
        newTournament.setStatus(TOURNAMENT_STATUS.PLANNING);
        newTournament.setBracketType(dto.getBracketType());
        newTournament.setSeeding_strategy(dto.getSeedingStrategy());
        tournamentRepository.save(newTournament);
    }

    public void joinTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            throw new TournamentDoesNotExistException("Tournament with id: " + tournamentId + " doesn't exist.");
        }
        Set<User> players = tournament.getPlayers();
        players.add(userService.getCurrentUser());
        tournamentRepository.save(tournament);
    }

    public TournamentStartResponseDto startTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            throw new TournamentDoesNotExistException("Tournament with id: " + tournamentId + " doesn't exist.");
        }
        if (tournament.getStatus() != TOURNAMENT_STATUS.PLANNING) {
            throw new TournamentHasAlreadyBeenStartedException("Tournament with id: " + tournamentId + " has already been played or started.");
        }
        if (!tournament.getOrganizer().equals(userService.getCurrentUser())) {
            throw new WrongUserException("Attempted to start tournament while not being it's orginizer.");
        }
        if (tournament.getPlayers().isEmpty()) {
            throw new NoPlayersException("No players have joined tournament with id: " + tournamentId + " cannot start");
        }
        tournament.setStatus(TOURNAMENT_STATUS.LIVE);
        tournament.setBracket(bracketService.initBracket(tournament));
        bracketService.seedBracket(tournament);
        tournamentRepository.save(tournament);

        TournamentStartResponseDto dto = new TournamentStartResponseDto();
        dto.setBracketId(tournament.getBracket().getId());

        return dto;
    }

    public List<TournamentSimpleDto> getAllTournaments(Integer page, Integer size, String field, Sort.Direction direction) {
        Sort sort = new Sort(direction, field);
        Pageable pageable = new PageRequest(page, size, direction, field);
        Page<Tournament> tournaments = tournamentRepository.findAll(pageable);
        return tournaments.getContent().stream()
                .map(tournament -> new TournamentSimpleDto(tournament.getId(), tournament.getName(), tournament.getCreationDate(), tournament.getEventDate()))
                .collect(Collectors.toList());
    }

    public void modifyTournament(Long tournamentId, EditTournamentDto dto) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            throw new TournamentDoesNotExistException("Tournament with id: " + tournamentId + " doesn't exist.");
        }
        if (StringUtils.isNotBlank(dto.getName())) tournament.setName(dto.getName());
        if (dto.getBracketType() != null) tournament.setBracketType(dto.getBracketType());
        if (dto.getSeedingStrategy() != null) tournament.setSeeding_strategy(dto.getSeedingStrategy());
        if (dto.getEventDate() != null) tournament.setEventDate(dto.getEventDate());
        tournamentRepository.save(tournament);
    }

    public TournamentDto getTournamentDetails(Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            throw new TournamentDoesNotExistException("Tournament with id: " + tournamentId + " doesn't exist.");
        }
        return TournamentDto.asDto(tournament);
    }
}