package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.TestCase;
import kazior.paulina.easytest.model.TestScenario;
import kazior.paulina.easytest.repository.TestScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class TestScenarioService {

    private final TestScenarioRepository testScenarioRepository;


    @Transactional(readOnly = true)
    public Collection<TestScenario> findAll() {
        return testScenarioRepository.findAll();
    }

    @Transactional
    public TestScenario saveTestScenario(TestScenario testScenario){
        return testScenarioRepository.save(testScenario);
    }

    @Transactional
    public TestScenario findTestScenarioByIdWithAllDetails(long testScenarioId) {
        return testScenarioRepository.findByIdWithDetails(testScenarioId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public TestScenario editTestScenario(long id, TestScenario newTestScenario){
        TestScenario testScenarioToEdit = findTestScenarioById(id);
        testScenarioToEdit.setTitle(newTestScenario.getTitle());
        return testScenarioToEdit;
    }

    @Transactional
    public TestScenario addTestCaseToTestScenario(long testScenarioId, TestCase testCase){
        TestScenario testScenario= testScenarioRepository.findByIdWithDetails(testScenarioId).orElseThrow(EntityNotFoundException::new);
        testScenario.getTestCases().add(testCase);
        testCase.setTestScenario(testScenario);
        return testScenario;
    }



    TestScenario findTestScenarioById(long id){
        return testScenarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
