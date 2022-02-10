package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.AppUser;
import kazior.paulina.easytest.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    AppUser findById(long userId) {
        return appUserRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public AppUser findSingleAppUserByUsername(String username) {
        return appUserRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

}
