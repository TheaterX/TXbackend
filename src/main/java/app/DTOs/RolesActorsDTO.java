package app.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesActorsDTO {
    private RoleActorDTO actor;
    private String roleName;
    private String description;
}
