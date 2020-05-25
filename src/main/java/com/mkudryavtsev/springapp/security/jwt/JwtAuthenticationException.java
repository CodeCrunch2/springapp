package com.mkudryavtsev.springapp.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception.
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
