package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.match.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}
