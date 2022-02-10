package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}
