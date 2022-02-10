package kazior.paulina.easytest.controller;

import kazior.paulina.easytest.service.TestStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testStep")
@RequiredArgsConstructor
@CrossOrigin
public class TestStepController {

    private final TestStepService testStepService;

}
