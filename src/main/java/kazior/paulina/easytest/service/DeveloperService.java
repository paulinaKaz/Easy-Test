package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.Developer;
import kazior.paulina.easytest.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Transactional(readOnly = true)
    public Collection<Developer> findAll() {
        return developerRepository.findAll();
    }


    private Developer testDeveloper(){
        return new Developer("Olaf", "Nowak", "razeaz","dffff", "dfdffff", "655465656");
    }

    public Developer saveDeveloper(){
        Developer developer = testDeveloper();
        return developerRepository.save(developer);

    }

    Developer findById(long developerId) {
        return developerRepository.findById(developerId).orElseThrow(EntityNotFoundException::new);
    }


}
