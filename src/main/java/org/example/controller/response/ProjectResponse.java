package org.example.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String projectName;
    private LocalDate startTime;
    private Integer budget;
}
