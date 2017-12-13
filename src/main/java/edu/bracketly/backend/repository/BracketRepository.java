package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BracketRepository extends JpaRepository<Bracket, Long> {
}
