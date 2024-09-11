package net.javaguides.sms.client;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    public AuthenticationFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String sessionToken = request.getHeader("Authorization");

        if (sessionToken == null || !authClient.validateSession(sessionToken)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid or missing session");
            return;
        }

        filterChain.doFilter(request, response);  // Continue if the session is valid
    }
}
