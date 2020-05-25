package com.mkudryavtsev.springapp.security.jwt;

import com.mkudryavtsev.springapp.model.Role;
import com.mkudryavtsev.springapp.model.Status;
import com.mkudryavtsev.springapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 */

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getLastPasswordChangeDate(),
                user.getStatus().equals(Status.ACTIVE),
                user.getPhoneNumber(),
                mapToGrantedAuthorities(user.getRoles())

        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
