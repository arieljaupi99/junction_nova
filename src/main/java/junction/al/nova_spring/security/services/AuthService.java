package junction.al.nova_spring.security.services;

import junction.al.nova_spring.security.model.AuthenticationRequest;
import junction.al.nova_spring.security.model.AuthenticationResponse;
import junction.al.nova_spring.security.model.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest registerRequest);

    String logout(String token);
}
