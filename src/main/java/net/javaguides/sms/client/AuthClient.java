package net.javaguides.sms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import net.javaguides.sms.dto.UserDto;

@FeignClient(name = "auth-service", url = "http://localhost:8081")  // Replace with actual auth service URL
public interface AuthClient {

    @PostMapping("/auth/login")
    ResponseEntity<String> login(@RequestBody UserDto userDto);

    @PostMapping("/auth/signup")
    ResponseEntity<String> signup(@RequestBody UserDto userDto);
    
    @PostMapping("/auth/validate")
    boolean validateSession(@RequestHeader("Authorization") String sessionToken);
}
