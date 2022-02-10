package kazior.paulina.easytest.service;

import kazior.paulina.easytest.model.Tester;
import kazior.paulina.easytest.repository.TesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TesterService {

    private final TesterRepository testerRepository;

    @Transactional(readOnly = true)
    public Collection<Tester> findAll() {
        return testerRepository.findAll();
    }

    @Transactional
    public Tester saveTester(Tester tester){
        return testerRepository.save(tester);
    }

    Tester findById(long testerId) {
        return testerRepository.findById(testerId).orElseThrow(EntityNotFoundException::new);
    }



}
