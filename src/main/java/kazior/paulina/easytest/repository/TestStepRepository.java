package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.TestStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestStepRepository extends JpaRepository<TestStep, Long> {
}
