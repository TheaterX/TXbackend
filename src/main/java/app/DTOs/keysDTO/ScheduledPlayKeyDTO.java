package app.DTOs.keysDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ScheduledPlayKeyDTO {
    private Integer playId;
    private String sceneName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date scheduledDate;
}
