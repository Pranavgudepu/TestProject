package com.TestProject.TestProject.controller;

import com.TestProject.TestProject.dto.LoginRequest;
import com.TestProject.TestProject.dto.RegisterRequest;
import com.TestProject.TestProject.dto.TokenResponse;
import com.TestProject.TestProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest)
    {
        return authService.register(registerRequest);
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest)
    {

        String token =authService.login(loginRequest.getUserName(),loginRequest.getPassword());
        TokenResponse response = new TokenResponse();
        response.setAccessToken(token);
        return response;
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, authenticated user!");
    }
}
