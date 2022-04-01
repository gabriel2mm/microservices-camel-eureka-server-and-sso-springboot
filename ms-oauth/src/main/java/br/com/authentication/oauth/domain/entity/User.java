package br.com.authentication.oauth.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    private Profile profile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.profile == null || this.profile.getPermissions() == null || this.profile.getPermissions().size() <= 0){
            return new ArrayList<>();
        }
        return this.profile.getPermissions();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

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
}
