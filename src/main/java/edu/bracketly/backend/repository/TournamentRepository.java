package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.Tournament;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TournamentRepository extends PagingAndSortingRepository<Tournament, Long> {

}
