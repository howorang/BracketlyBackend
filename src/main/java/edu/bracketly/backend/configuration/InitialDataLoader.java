package edu.bracketly.backend.configuration;

import edu.bracketly.backend.model.entity.BRACKET_TYPE;
import edu.bracketly.backend.model.entity.SEEDING_STRATEGY;
import edu.bracketly.backend.model.entity.Tournament;
import edu.bracketly.backend.model.entity.user.Player;
import edu.bracketly.backend.model.flow.TOURNAMENT_STATUS;
import edu.bracketly.backend.repository.PlayerRepository;
import edu.bracketly.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (playerRepository.existsByUsername("test")) return;
        Player test = new Player();
        test.setUsername("test");
        test.setPassword(passwordEncoder.encode("test"));
        ;
        playerRepository.save(test);

        Player howo = new Player();
        howo.setUsername("howo");
        howo.setPassword(passwordEncoder.encode("test"));
        playerRepository.save(howo);

        Set<Player> players = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            Player player = new Player();
            player.setUsername("test" + i);
            player.setPassword(passwordEncoder.encode("test" + i));
            playerRepository.save(player);
            players.add(playerRepository.findByUsername(player.getUsername()));
        }

        Tournament tournament = new Tournament();
        tournament.setName("evo");
        tournament.setSeeding_strategy(SEEDING_STRATEGY.RANDOM);
        tournament.setCreationDate(new Date());
        tournament.setEventDate(new Date());
        tournament.setBracketType(BRACKET_TYPE.SINGLE_ELIMINATION);
        tournament.setOrganizer(playerRepository.findByUsername("test"));
        tournament.setPlayers(players);
        tournament.setStatus(TOURNAMENT_STATUS.PLANNING);
        tournamentRepository.save(tournament);
    }

}
