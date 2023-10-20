package junction.al.nova_spring.security.entities;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.security.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document("users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    private String id;
    @Field(name = "username")
    @Indexed(unique = true)
    private String username;
    @Field(name = "password")
    private String password;
    @Getter
    @Field(name = "role")
    private Role role;
    private Contract contract;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getDesc()));
    }
}
