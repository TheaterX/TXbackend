package app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class SearchPlaysDTO {
    private Date date;
    private String scene;
    private Integer playId;
    private Integer repertoire;
}
