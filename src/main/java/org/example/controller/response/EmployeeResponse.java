package org.example.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponse {
    private Long id;
    private Long projectId;
    private String name;
    private String position;
    private String projectName;
    private LocalDate hiringTime;
    private Double salary;
}
