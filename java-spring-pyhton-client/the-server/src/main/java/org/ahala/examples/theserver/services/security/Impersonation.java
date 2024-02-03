package org.ahala.examples.theserver.services.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ahala.examples.theserver.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Impersonation implements UserDetails {

    private long id;
    private String email;

    public static Impersonation fromUser(AppUser user) {
        return Impersonation.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static Impersonation fromClaims(Claims claims) {
        long id = Long.parseLong(claims.getId());

        return Impersonation.builder()
                .id(id)
                .email(claims.getSubject())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
