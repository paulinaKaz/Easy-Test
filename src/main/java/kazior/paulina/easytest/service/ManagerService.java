package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.Manager;
import kazior.paulina.easytest.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Transactional(readOnly = true)
    public Collection<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Transactional
    public Manager saveManager(Manager manager){
        return managerRepository.save(manager);
    }


    @Transactional
    public Manager findByUserName(String userName){
        return managerRepository.findByUsername(userName).orElseThrow(EntityNotFoundException::new);
    }

    Manager findById(long managerId) {
        return managerRepository.findById(managerId).orElseThrow(EntityNotFoundException::new);
    }

}
