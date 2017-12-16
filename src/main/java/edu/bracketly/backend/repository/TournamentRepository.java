package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
