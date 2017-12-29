package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.user.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
}
