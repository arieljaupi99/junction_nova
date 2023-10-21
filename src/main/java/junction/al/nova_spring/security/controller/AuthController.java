package junction.al.nova_spring.security.controller;

import junction.al.nova_spring.security.model.AuthenticationRequest;
import junction.al.nova_spring.security.model.AuthenticationResponse;
import junction.al.nova_spring.security.model.RegisterRequest;
import junction.al.nova_spring.security.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/auth")

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(this.authService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest registerRequest
    ) {
        return ResponseEntity.ok(this.authService.authenticate(registerRequest));
    }

    @PostMapping("/logout")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization") String token
    ) {
        String jwtToken = token.substring(7);
        return ResponseEntity.ok(this.authService.logout(jwtToken));
    }
}
