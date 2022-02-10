package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p FROM Project p " +
            "LEFT JOIN FETCH p.testScenarios " +
            "LEFT JOIN FETCH p.testers " +
            "LEFT JOIN FETCH p.developers " +
            "LEFT JOIN FETCH p.manager " +
            "WHERE p.id = :projectId")
    Optional<Project> findByIdWithDetails(Long projectId);

    Optional<Project> findById(long id);

    List<Project> findAll();


}
