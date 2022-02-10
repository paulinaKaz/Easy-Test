package kazior.paulina.easytest.controller;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.Project;
import kazior.paulina.easytest.model.TestScenario;
import kazior.paulina.easytest.service.ProjectService;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/projects")
@CrossOrigin
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<Project>> getAllProjects() {
        return new ResponseEntity<>(projectService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getSingleProject(@PathVariable long projectId){
        return new ResponseEntity<>(projectService.findByIdWithDetails(projectId), HttpStatus.OK);
    }

    @PostMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Project> addNewProject(@RequestBody @Valid Project project){
        return new ResponseEntity<>(projectService.addProject(project),HttpStatus.CREATED);
        //return ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectId}")
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Project> editProject(@PathVariable long projectId, @RequestBody Project project){
        return new ResponseEntity<>(projectService.editProject(projectId, project), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/testScenario")
    public ResponseEntity<Project> addTestScenarioToProject(@PathVariable long projectId, @RequestBody TestScenario testScenario){
        return new ResponseEntity<>(projectService.addTestScenarioToProject(projectId, testScenario), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/tester/{testerId}")
    public ResponseEntity<Project> assignTesterToProject(@PathVariable long projectId, @PathVariable long testerId){
        return new ResponseEntity<>(projectService.assignTesterToProject(projectId, testerId), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/developer/{developerId}")
    public ResponseEntity<Project> assignDeveloperToProject(@PathVariable long projectId, @PathVariable long developerId){
        return new ResponseEntity<>(projectService.assignDeveloperToProject(projectId, developerId), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProject(@PathVariable long projectId){
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }


}