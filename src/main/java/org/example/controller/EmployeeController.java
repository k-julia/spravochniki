package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.EmployeeRequest;
import org.example.domain.Employee;
import org.example.domain.Project;
import org.example.repository.EmployeeRepository;
import org.example.controller.response.EmployeeResponse;
import org.example.repository.ProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployees(@RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                               @RequestParam(defaultValue = "id") String fieldName) {
        List<Employee> employees = employeeRepository.findAll(Sort.by(direction, fieldName));

        return employees.stream().map(this::transformEmployeeRequest).collect(Collectors.toList());
    }

    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository
                .save(transformEmployeeRequest(new Employee(), employeeRequest));

        return transformEmployeeRequest(employee);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponse editEmployee(@PathVariable(name = "id") Long id,
                                        @RequestBody EmployeeRequest employeeRequest) throws InstanceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new InstanceNotFoundException("No employee with id {}" + id);
        }

        employee = employeeRepository.save(transformEmployeeRequest(employee, employeeRequest));
        return transformEmployeeRequest(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeResponse transformEmployeeRequest(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setHiringTime(employee.getHiringTime());
        response.setSalary(employee.getSalary());
        response.setPosition(employee.getPosition());
        response.setProjectId(employee.getProject().getId());
        response.setProjectName(employee.getProject().getName());
        return response;
    }

    private Employee transformEmployeeRequest(Employee employee, EmployeeRequest request) {
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setHiringTime(request.getHiringTime());
        employee.setSalary(request.getSalary());

        Project project = projectRepository.findById(request.getProjectId()).orElse(null);
        employee.setProject(project);

        return employee;
    }
}
