package app.DTOs.deleteDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteSeatDTO {
    private int row;
    private int number;
    private String sceneName;
}
