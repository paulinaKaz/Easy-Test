package kazior.paulina.easytest.controller;

import kazior.paulina.easytest.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin
public class ManagerController {

    private final ManagerService managerService;

}
