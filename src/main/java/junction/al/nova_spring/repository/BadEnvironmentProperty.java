package junction.al.nova_spring.repository;

public class BadEnvironmentProperty extends RuntimeException {
    public BadEnvironmentProperty(String message) {
        super(message);
    }
}
