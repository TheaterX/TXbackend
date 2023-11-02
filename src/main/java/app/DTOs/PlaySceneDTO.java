package app.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaySceneDTO {
    private String playName;
    private String forScene;

    public PlaySceneDTO(String playName, String forScene) {
        this.playName = playName;
        this.forScene = forScene;
    }

    public PlaySceneDTO() {
    }
}
