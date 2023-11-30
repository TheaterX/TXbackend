package app.DTOs;

import app.DTOs.keysDTO.ScheduledPlayKeyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScheduledPlayDTO {
    private ScheduledPlayKeyDTO id;
    private boolean premiere;
    private String adminUser;
    private boolean guest;
    private boolean travels;
    private String guestPlayName;
}
