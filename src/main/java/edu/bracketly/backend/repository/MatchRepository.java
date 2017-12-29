package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>, QueryDslPredicateExecutor<Match> {
}
