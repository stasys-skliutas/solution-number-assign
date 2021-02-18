package dev.stasys.numberassignservice.externalapi;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class AssignResultResponse {
    private String inputString;
    private Integer value;
}
