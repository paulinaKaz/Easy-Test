package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {

    Optional<Tester> findById(Long id);

    List<Tester> findAll();

    Optional<Tester> findById(long id);
}
