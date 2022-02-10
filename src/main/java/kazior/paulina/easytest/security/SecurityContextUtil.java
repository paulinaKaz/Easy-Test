package kazior.paulina.easytest.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {

    public static String getUserFromSecurityContext(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
