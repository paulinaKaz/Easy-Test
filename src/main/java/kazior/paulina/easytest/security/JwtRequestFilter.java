package kazior.paulina.easytest.security;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService jwtUserDetailService;

    private final JwtTokenUtil jwtTokenUtil;


    @Value("${jwt.get.token.uri}")
    private String authenticationPath;

    private final String tokenPrefix = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request.getMethod().equals("OPTIONS") || request.getRequestURI().equals(authenticationPath)) {
            chain.doFilter(request, response);
            return;
        }

        log.info("Authentication Request For '{}'", request.getRequestURL());

        String requestTokenHeader = request.getHeader(AUTHORIZATION);

        if (requestTokenHeader != null && requestTokenHeader.startsWith(tokenPrefix)) {
            String jwtToken = requestTokenHeader.substring(tokenPrefix.length());
            try {
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                String userRole = jwtTokenUtil.getRoleFromToken(jwtToken);

                if (!jwtTokenUtil.isTokenExpired(jwtToken)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, convertRoleToAuthorities(userRole));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            } catch (SignatureException ex) {
                log.warn("Unable to get username from JWT token. Invalid JWT signature.");
            } catch (MalformedJwtException ex) {
                log.warn("Unable to get username from JWT token. Invalid JWT format.");
            }
        } else {
            log.warn("Authorization header does not start with 'Bearer ' or is missing");
        }

        chain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> convertRoleToAuthorities(String userRole) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole));
        return authorities;
    }
}
