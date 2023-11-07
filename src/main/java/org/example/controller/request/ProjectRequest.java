package org.example.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectRequest {
    private String name;
    private LocalDate startTime;
    private Integer budget;
}
