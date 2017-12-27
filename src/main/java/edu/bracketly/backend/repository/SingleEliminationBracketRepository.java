package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleEliminationBracketRepository extends JpaRepository<SingleEliminationBracket, Long> {
}
