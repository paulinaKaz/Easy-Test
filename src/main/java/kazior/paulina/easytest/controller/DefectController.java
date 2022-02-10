package kazior.paulina.easytest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.Comment;
import kazior.paulina.easytest.model.Defect;
import kazior.paulina.easytest.service.DefectService;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/defects")
@RequiredArgsConstructor
@CrossOrigin
public class DefectController {

    private final DefectService defectService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<Defect>> getAllDefects() {
        return new ResponseEntity<>(defectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{defectId}")
    public ResponseEntity<Defect> getSingleDefect(@PathVariable long defectId){
        return new ResponseEntity<>(defectService.findByIdWithDetails(defectId), HttpStatus.OK);
    }

    @PutMapping("/{defectId}/appUser/{appUserId}")
    public ResponseEntity<Defect> assignUserToDefect(@PathVariable long defectId, @PathVariable long appUserId){
        return new ResponseEntity<>(defectService.assignUserToDefect(defectId, appUserId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Defect> addNewDefect(@RequestBody Defect defect){
        return new ResponseEntity<>(defectService.addNewDefect(defect), HttpStatus.CREATED);
    }

    @PutMapping("/{defectId}/testCase/{testCaseId}")
    public ResponseEntity<Defect> assignTestCaseToDefect(@PathVariable long defectId, @PathVariable long testCaseId){
        return new ResponseEntity<>(defectService.assignTestCaseToDefect(defectId, testCaseId), HttpStatus.OK);
    }

    @PostMapping("/{defectId}/comment")
    public ResponseEntity<Defect> addCommentToDefect(@PathVariable long defectId, @RequestBody Comment comment){
        return new ResponseEntity<>(defectService.addCommentToDefect(defectId, comment), HttpStatus.OK);
    }

    @DeleteMapping("/{defectId}")
    public ResponseEntity deleteDefect(@PathVariable long defectId){
        defectService.deleteDefect(defectId);
        return ResponseEntity.noContent().build();
    }
}
