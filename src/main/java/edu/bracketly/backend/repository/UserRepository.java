package edu.bracketly.backend.repository;

import edu.bracketly.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
