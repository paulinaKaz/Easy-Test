package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findById(Long id);

    Optional<Manager> findByUsername(String userName);

    List<Manager> findAll();

}
