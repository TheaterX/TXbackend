package app.DTOs.GetDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFullPlayDTO {
    private float grade;
    private String genres;
    private String directors;
    private String awards;
    private List<GetRoleDTO> roles;
}
