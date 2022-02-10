package kazior.paulina.easytest.repository;


import kazior.paulina.easytest.model.TestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestScenarioRepository extends JpaRepository<TestScenario, Long> {


    Optional<TestScenario> findById(long testScenarioId);

    List<TestScenario> findAll();

    @Query("SELECT s FROM TestScenario s " +
            "LEFT JOIN FETCH s.project " +
            "WHERE s.id = :testScenarioId")
    Optional<TestScenario> findByIdWithDetails(long testScenarioId);
}
