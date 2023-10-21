package junction.al.nova_spring.security.services.impl;

import junction.al.nova_spring.security.services.TokenBlacklistService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final Set<String> revokedTokens = new HashSet<>();

    @Override
    public void revokeToken(String token) {
        revokedTokens.add(token);
    }

    @Override
    public boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }
}
