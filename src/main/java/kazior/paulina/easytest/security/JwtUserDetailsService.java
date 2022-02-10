package kazior.paulina.easytest.security;

import kazior.paulina.easytest.model.AppUser;
import kazior.paulina.easytest.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findSingleAppUserByUsername(username);
        return buildUserForAuthentication(appUser);
    }

    private UserDetails buildUserForAuthentication(AppUser appUser) {
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getAppUserRole().toString())
                .build();
    }
}
