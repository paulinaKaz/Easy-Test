package kazior.paulina.easytest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.Tester;
import kazior.paulina.easytest.service.TesterService;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/testers")
@CrossOrigin
@RequiredArgsConstructor
public class TesterController {

    private final TesterService testerService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<Tester>> getAll() {
        return new ResponseEntity<>(testerService.findAll(), HttpStatus.OK);
    }
/*    @GetMapping("/test3")
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Tester> addNewTester(){
        return new ResponseEntity<>(testerService.saveTester(),HttpStatus.CREATED);
        //return ResponseEntity.noContent().build();
    }*/

}
