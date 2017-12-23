package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.CreateUserDto;
import edu.bracketly.backend.dto.UserDetailsDto;
import edu.bracketly.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(CreateUserDto createUserDto) {
        userService.createUser(createUserDto.getUsername(), createUserDto.getPassword());
    }

    @GetMapping("/me")
    public UserDetailsDto aboutMe() {
        return userService.getLoggedInUserDetails();
    }
}
