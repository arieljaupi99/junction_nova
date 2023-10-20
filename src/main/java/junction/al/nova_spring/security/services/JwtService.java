package junction.al.nova_spring.security.services;

import junction.al.nova_spring.security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails user);

    String exctractUsername(String jwtToken);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);
}
