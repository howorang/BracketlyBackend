package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.user.Player;
import lombok.Data;

@Data
public class PlayerDto extends UserDto {
    private long rank;

    public static PlayerDto asDto(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setId(player.getId());
        dto.setUsername(player.getUsername());
        dto.setRank(player.getRank());
        return dto;
    }
}
