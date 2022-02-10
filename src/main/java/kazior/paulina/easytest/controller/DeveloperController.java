package kazior.paulina.easytest.controller;


import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.Developer;
import kazior.paulina.easytest.service.DeveloperService;
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
@RequestMapping("/developers")
@CrossOrigin
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<Developer>> getAll() {
        return new ResponseEntity<>(developerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/test2")
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Developer> addNewDeveloper(){
        return new ResponseEntity<>(developerService.saveDeveloper(),HttpStatus.CREATED);
        //return ResponseEntity.noContent().build();
    }

}
