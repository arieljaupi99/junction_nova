package junction.al.nova_spring.security.services;

public interface TokenBlacklistService {
    public void revokeToken(String token);
    public boolean isTokenRevoked(String token);
}
