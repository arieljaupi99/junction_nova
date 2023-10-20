package junction.al.nova_spring.security.services.impl;

import junction.al.nova_spring.repository.BadEnvironmentProperty;
import junction.al.nova_spring.security.entities.User;
import junction.al.nova_spring.security.enums.Role;
import junction.al.nova_spring.security.model.AuthenticationRequest;
import junction.al.nova_spring.security.model.AuthenticationResponse;
import junction.al.nova_spring.security.model.RegisterRequest;
import junction.al.nova_spring.security.repo.UserRepo;
import junction.al.nova_spring.security.services.AuthService;
import junction.al.nova_spring.security.services.JwtService;
import junction.al.nova_spring.security.services.TokenBlacklistService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo, JwtService jwtService, AuthenticationManager authenticationManager, TokenBlacklistService tokenBlacklistService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role roleEnum;
        String role = registerRequest.getRole();
        switch (role) {
            case "user" -> roleEnum = Role.USER;
            case "admin" -> roleEnum = Role.ADMIN;
            default -> throw new BadEnvironmentProperty("Role does not exist");
        }
        user.setRole(roleEnum);
        this.userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public String logout(String token) {
        tokenBlacklistService.revokeToken(token);
        return "Logged out successfully.";
    }
}
