package kazior.paulina.easytest.service;

import kazior.paulina.easytest.model.TestCase;
import kazior.paulina.easytest.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;

    @Transactional(readOnly = true)
    public TestCase findById(long userId) {
        return testCaseRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }


}
