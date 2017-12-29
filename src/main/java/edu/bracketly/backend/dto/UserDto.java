package edu.bracketly.backend.dto;

import lombok.Data;

@Data
public class UserDto {
    String type;
    private long id;
    private String username;

    public UserDto() {
        type = UserDto.class.getSimpleName();
    }
}
