package com.mkudryavtsev.springapp.config;


import com.mkudryavtsev.springapp.security.jwt.JwtConfigurer;
import com.mkudryavtsev.springapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Spring Security configuration class.
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String DEVELOPERS_ENDPOINT = "/api/v1/developers/**";
    private static final String SKILLS_ENDPOINT = "/api/v1/skills/**";
    private static final String ACCOUNTS_ENDPOINT = "/api/v1/accounts/**";
    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, DEVELOPERS_ENDPOINT, SKILLS_ENDPOINT,ACCOUNTS_ENDPOINT).hasRole("USER")
                .antMatchers(DEVELOPERS_ENDPOINT, ACCOUNTS_ENDPOINT).hasRole("MODERATOR")
                .anyRequest().hasRole("ADMIN")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
