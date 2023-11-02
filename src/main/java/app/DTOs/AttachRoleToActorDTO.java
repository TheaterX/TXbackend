package app.DTOs;

import app.DTOs.keysDTO.RoleKeyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttachRoleToActorDTO {
    private String actorUMCN;
    private RoleKeyDTO roleId;
}