package app.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSceneDTO {
    private String name;
    private int rows;
    private int seatsInRow;
}
