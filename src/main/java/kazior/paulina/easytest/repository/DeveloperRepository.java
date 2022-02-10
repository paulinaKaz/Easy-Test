package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.AppUser;
import kazior.paulina.easytest.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {



}
