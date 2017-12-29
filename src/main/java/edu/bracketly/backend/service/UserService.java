package edu.bracketly.backend.service;

import edu.bracketly.backend.dto.UserDto;
import edu.bracketly.backend.exception.UserAlreadyExistsException;
import edu.bracketly.backend.model.entity.user.Player;
import edu.bracketly.backend.model.entity.user.User;
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

        Player newUser = new Player();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);
    }

    public <T extends User> T getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (T) userRepository.findByUsername(username);
    }

    private boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDto getLoggedInUserDetails() {
        User currentUser = getCurrentUser();
        UserDto dto = new UserDto();
        dto.setId(currentUser.getId());
        dto.setUsername(currentUser.getUsername());
        return dto;
    }
}