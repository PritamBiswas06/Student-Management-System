package net.javaguides.sms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	
    	http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/login", "/students","/h2-console", "/").permitAll()  // Allow access to login and student page
            .anyRequest().authenticated()  // Other requests require authentication
        )
        .formLogin((form) -> form
            .loginPage("/login")  // Custom login page
            .defaultSuccessUrl("/students", true)  // Redirect to student page after successful login
            .permitAll()
        )
        .logout((logout) -> logout.permitAll());

    return http.build();
    	
//    	http
//        .authorizeHttpRequests((requests) -> requests
//            .requestMatchers("/login", "/h2-console/**").permitAll()  // Allow login and H2 console access
//            .anyRequest().authenticated()  // Secure all other pages
//        )
//        .formLogin((form) -> form
//            .loginPage("/login")   // Custom login page
//            .defaultSuccessUrl("/students", true)  // Redirect to the student page on successful login
//            .permitAll()
//        )
//        .logout((logout) -> logout.permitAll());
//
//    // Enable access to the H2 console
//    http.csrf().disable();  // Disable CSRF protection for the H2 console
//    http.headers().frameOptions().disable();  // Allow H2 to be displayed in iframes
//
//    return http.build();
    	
//    	http
//        .authorizeHttpRequests((requests) -> requests
//            .requestMatchers("/h2-console/**", "/login", "/").permitAll()  // Permit access to login and h2-console
//            .requestMatchers("/students").authenticated()  // Require authentication for student page
//            .anyRequest().authenticated()  // All other requests require authentication
//        )
//        .headers().frameOptions().disable()  // Disable frame options for H2 Console
//        .and()
//        .csrf().disable()  // Disable CSRF for now (you can refine this later)
//        .formLogin((form) -> form
//            .loginPage("/login")  // Custom login page mapping
//            .defaultSuccessUrl("/students", true)  // Redirect to student page after successful login
//            .permitAll()
//        )
//        .logout((logout) -> logout.permitAll());
//
//    return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

