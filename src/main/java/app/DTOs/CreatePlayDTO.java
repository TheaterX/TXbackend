package app.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatePlayDTO {
        private String name;
        private float grade = 3;
        private String scene;
        private List<String> genres;
        private List<String> directors;
        private List<RolesActorsDTO> roles;
}
