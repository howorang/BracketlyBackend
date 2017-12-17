package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.user.User;
import lombok.Data;

@Data
public class PlayerDto {
    private long id;
    private String username;
    private long rank;

    public static PlayerDto asDto(User user) {
        PlayerDto dto = new PlayerDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRank(user.getDetails().getRank());
        return dto;
    }
}
