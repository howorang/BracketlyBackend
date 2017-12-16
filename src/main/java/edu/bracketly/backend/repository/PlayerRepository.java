package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<User, Long> {
}
