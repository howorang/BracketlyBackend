package edu.bracketly.backend.service;

import edu.bracketly.backend.exception.UserAlreadyExistsException;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.entity.user.UserDetails;
import edu.bracketly.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String username, String password) {

        if (userExists(username)) {
            throw new UserAlreadyExistsException("User with name " + username + "already exists.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        UserDetails details = new UserDetails();
        newUser.setDetails(details);
        userRepository.save(newUser);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    private boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}