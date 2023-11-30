package app.DTOs.GetDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetScheduledPlaysDTO {
    private int playId;
    private String playName;
    private String sceneName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private int freeSeats;
    private String directors;
}
