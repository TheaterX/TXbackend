package app.DTOs;

import app.DTOs.keysDTO.RoleKeyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateRoleDTO {
    private RoleKeyDTO id;
    private String description;
}
