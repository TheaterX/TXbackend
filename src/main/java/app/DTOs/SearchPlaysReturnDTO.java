package app.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class SearchPlaysReturnDTO {
    private List<PlaySceneDTO> plays;
    private List<SceneNumberDTO> scenes;
    @JsonFormat(pattern="yyyy-MM-dd")
    private List<Date> dates;
}
