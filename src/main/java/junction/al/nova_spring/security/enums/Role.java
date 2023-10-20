package junction.al.nova_spring.security.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("user"),
    ADMIN("admin");
    private final String desc;
    Role(String desc) {
        this.desc = desc;
    }
}
