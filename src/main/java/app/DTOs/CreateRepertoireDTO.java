package app.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRepertoireDTO {
    private int year;
    private String name;
    private List<Integer> plays;
}
