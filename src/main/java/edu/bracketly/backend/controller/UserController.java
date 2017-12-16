package edu.bracketly.backend.controller;

import edu.bracketly.backend.dto.CreateUserDto;
import edu.bracketly.backend.exception.UserAlreadyExistsException;
import edu.bracketly.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(CreateUserDto createUserDto) throws UserAlreadyExistsException {
        userService.createUser(createUserDto.getUsername(), createUserDto.getPassword());
    }

}
