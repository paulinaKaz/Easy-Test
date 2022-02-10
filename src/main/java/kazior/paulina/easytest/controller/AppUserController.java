package kazior.paulina.easytest.controller;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.model.AppUser;
import kazior.paulina.easytest.service.AppUserService;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/appUsers")
@RequiredArgsConstructor
@CrossOrigin
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    @JsonView(JsonViewFilter.BasicInfo.class)
    public ResponseEntity<Collection<AppUser>> getAllUsers() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

}
