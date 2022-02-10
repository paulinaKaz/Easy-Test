package kazior.paulina.easytest.service;

import kazior.paulina.easytest.repository.TestStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestStepService {

    private final TestStepRepository testStepRepository;
}
