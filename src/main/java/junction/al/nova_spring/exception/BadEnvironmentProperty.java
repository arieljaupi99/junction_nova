package junction.al.nova_spring.exception;

public class BadEnvironmentProperty extends RuntimeException {
    public BadEnvironmentProperty(String message) {
        super(message);
    }
}
