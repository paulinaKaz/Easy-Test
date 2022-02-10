package kazior.paulina.easytest.security;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwtToken;

}
