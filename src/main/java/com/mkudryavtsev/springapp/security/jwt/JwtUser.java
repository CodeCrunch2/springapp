package com.mkudryavtsev.springapp.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mkudryavtsev.springapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Spring Security wrapper for class {@link User}.
 */

public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Date lastPasswordChangeDate;
    private final boolean enabled;
    private final String phoneNumber;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id,
                   String username,
                   String password,
                   Date lastPasswordChangeDate,
                   boolean enabled,
                   String phoneNumber,
                   Collection<? extends GrantedAuthority> authorities) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.lastPasswordChangeDate = lastPasswordChangeDate;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordChangeDate() {
        return lastPasswordChangeDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
