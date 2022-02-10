package kazior.paulina.easytest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kazior.paulina.easytest.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtil {

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.in.seconds}")
    private Long expiration;

    String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        Date createdDate = new Date();
        Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(appUser.getUsername())
                .claim("userRole", appUser.getAppUserRole())
                .claim("firstName", appUser.getFirstName())
                .claim("lastName", appUser.getLastName())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    String getRoleFromToken(String token) {
        return getAllClaimsFromToken(token).get("userRole", String.class);
    }

    Boolean isTokenExpired(String token) {
        Date expiration = getAllClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }


}
