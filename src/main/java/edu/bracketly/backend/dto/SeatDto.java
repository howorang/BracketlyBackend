package edu.bracketly.backend.dto;

import edu.bracketly.backend.model.entity.bracket.Seat;
import lombok.Data;

@Data
public class SeatDto {
    private long id;
    private PlayerDto player;

    public static SeatDto asDto(Seat seat) {
        SeatDto dto = new SeatDto();
        dto.setId(seat.getId());
        if (seat.getPlayer() != null) dto.setPlayer(PlayerDto.asDto(seat.getPlayer()));
        return dto;
    }
}
