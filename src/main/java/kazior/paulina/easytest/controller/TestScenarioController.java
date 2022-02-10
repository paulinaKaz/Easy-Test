package kazior.paulina.easytest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.TestCase;
import kazior.paulina.easytest.model.TestScenario;
import kazior.paulina.easytest.service.TestScenarioService;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/testScenarios")
@CrossOrigin
@RequiredArgsConstructor
public class TestScenarioController {

    private final TestScenarioService testScenarioService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<TestScenario>> getAllTestScenario() {
        return new ResponseEntity<>(testScenarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{testScenarioId}")
    public ResponseEntity<TestScenario> getSingleTestScenario(@PathVariable long testScenarioId){
        return new ResponseEntity<>(testScenarioService.findTestScenarioByIdWithAllDetails(testScenarioId), HttpStatus.OK);
    }

    @PutMapping("/{testScenarioId}")
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<TestScenario> editTestScenario(@PathVariable long testScenarioId, @RequestBody TestScenario testScenario){
        return new ResponseEntity<>(testScenarioService.editTestScenario(testScenarioId, testScenario), HttpStatus.OK);
    }

    @PostMapping("/{testScenarioId}/testCase")
    public ResponseEntity<TestScenario> addTestCaseToTestScenario(@PathVariable long testScenarioId, @RequestBody TestCase testCase){
        return new ResponseEntity<>(testScenarioService.addTestCaseToTestScenario(testScenarioId, testCase), HttpStatus.OK);
    }
}
