package kazior.paulina.easytest.repository;

import kazior.paulina.easytest.model.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {

    @Query("SELECT d FROM Defect d " +
            "LEFT JOIN FETCH d.testCases " +
            "LEFT JOIN FETCH d.assignUser " +
            "LEFT JOIN FETCH d.comments " +
            "WHERE d.id = :defectId")
    Optional<Defect> findByIdWithDetails(long defectId);

    Optional<Defect> findById(long id);

    List<Defect> findAll();

}
