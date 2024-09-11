package net.javaguides.sms.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private AuthClient authClient;

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .requestMatchers("/auth/login", "/auth/signup").permitAll()  // Allow login and signup without authentication
                .anyRequest().authenticated()  // Protect other endpoints
            .and()
            .addFilterBefore(new AuthenticationFilter(authClient), UsernamePasswordAuthenticationFilter.class);
    }
}
