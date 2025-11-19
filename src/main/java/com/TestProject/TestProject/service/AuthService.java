package com.TestProject.TestProject.service;

import com.TestProject.TestProject.dto.RegisterRequest;
import com.TestProject.TestProject.model.User;
import com.TestProject.TestProject.repository.UserRepository;
import com.TestProject.TestProject.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    private  final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public String register(RegisterRequest registerRequest)
    {
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return "User Registered successfully";
    }

    public String login(String username,String password)
    {
        User user= userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("User not found"));
        if (!bCryptPasswordEncoder.matches(password,user.getPassword()))
        {
            throw new RuntimeException("Invalid Password");
        }
        String token =jwtService.generateAccessToken(username);
        user.setAccessToken(token);
        userRepository.save(user);
        return token;
    }
}
