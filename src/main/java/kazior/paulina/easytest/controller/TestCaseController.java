package kazior.paulina.easytest.controller;

import kazior.paulina.easytest.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testCase")
@RequiredArgsConstructor
@CrossOrigin
public class TestCaseController {

    private final TestCaseService testCaseService;
}
