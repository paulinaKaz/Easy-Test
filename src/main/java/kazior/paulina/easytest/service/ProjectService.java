package kazior.paulina.easytest.service;

import kazior.paulina.easytest.model.*;
import kazior.paulina.easytest.repository.ProjectRepository;
import kazior.paulina.easytest.security.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final  TesterService testerService;
    private final ManagerService managerService;
    private final DeveloperService developerService;


    @Transactional
    public Project addProject(Project project){
        Manager manager = managerService.findByUserName(SecurityContextUtil.getUserFromSecurityContext());

        project.setManager(manager);
        manager.getProjects().add(project);
        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project findByIdWithDetails(long id){
        return projectRepository.findByIdWithDetails(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Project editProject(long id, Project newProject){
        Project projectToEdit = findProjectById(id);
        projectToEdit.setProjectName(newProject.getProjectName());
        projectToEdit.setDescription(newProject.getDescription());
        projectToEdit.setProjectSignature(newProject.getProjectSignature());
        return projectToEdit;
    }

    @Transactional
    public Project addTestScenarioToProject(long projectId, TestScenario testScenario){
        Project project = projectRepository.findByIdWithDetails(projectId).orElseThrow(EntityNotFoundException::new);
        project.getTestScenarios().add(testScenario);
        testScenario.setProject(project);
        return project;
    }

    @Transactional
    public Project assignTesterToProject(long projectId, long testerId){
        Project project = projectRepository.findByIdWithDetails(projectId).orElseThrow(EntityNotFoundException::new);
        Tester tester = testerService.findById(testerId);
        project.getTesters().add(tester);
        tester.getProjects().add(project);
        return project;
    }

    @Transactional
    public Project assignDeveloperToProject(long projectId, long developerId){
        Project project = projectRepository.findByIdWithDetails(projectId).orElseThrow(EntityNotFoundException::new);
        Developer developer = developerService.findById(developerId);
        project.getDevelopers().add(developer);
        developer.getProjects().add(project);
        return project;
    }

    @Transactional
    public void deleteProject(long projectId){
        projectRepository.deleteById(projectId);
    }

    Project findProjectById(long id){
        return projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
