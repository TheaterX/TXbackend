package app.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditRepertoireDTO {
    private int year;
    private List<Integer> plays;
}
