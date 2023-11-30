package app.DTOs.GetDTOs;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSeatDTO {
    private int row;
    private int number;
    @Nullable
    private String username;

    public GetSeatDTO(int row, int number) {
        this.row = row;
        this.number = number;
    }
}
