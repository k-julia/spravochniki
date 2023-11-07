package org.example.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequest {
    private Long projectId;
    private String name;
    private String position;
    private LocalDate hiringTime;
    private Double salary;
}
