package edu.bracketly.backend.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
}
