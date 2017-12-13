package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.bracket.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
