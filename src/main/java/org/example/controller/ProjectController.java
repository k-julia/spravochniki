package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.ProjectRequest;
import org.example.controller.response.ProjectResponse;
import org.example.domain.Project;
import org.example.repository.ProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;

    @GetMapping("/project")
    public List<ProjectResponse> getProjects(@RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                             @RequestParam(defaultValue = "id") String fieldName) {
        List<Project> projects = projectRepository.findAll(Sort.by(direction, fieldName));

        return projects.stream().map(this::transformProjectRequest).collect(Collectors.toList());
    }

    @PostMapping("/project")
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {
        Project project = projectRepository
                .save(transformProjectRequest(new Project(), projectRequest));

        return transformProjectRequest(project);
    }

    @PutMapping("/project/{id}")
    public ProjectResponse editProject(@PathVariable(name = "id") Long id,
                                         @RequestBody ProjectRequest projectRequest) throws InstanceNotFoundException {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            throw new InstanceNotFoundException("No project with id {}" + id);
        }

        project = projectRepository.save(transformProjectRequest(project, projectRequest));
        return transformProjectRequest(project);
    }

    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable(name = "id") Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectResponse transformProjectRequest(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setProjectName(project.getName());
        response.setBudget(project.getBudget());
        response.setStartTime(project.getStartTime());
        return response;
    }

    private Project transformProjectRequest(Project project, ProjectRequest request) {
        project.setName(request.getName());
        project.setBudget(request.getBudget());
        project.setStartTime(request.getStartTime());

        return project;
    }
}
