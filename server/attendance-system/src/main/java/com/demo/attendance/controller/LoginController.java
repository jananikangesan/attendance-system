package com.demo.attendance.controller;


import com.demo.attendance.service.UserService;
import com.demo.attendance.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (userService.authenticate(username, password)) {
            String token = jwtService.generateToken(username);
            return Map.of("token", token);
        } else {
            return Map.of("message", "Invalid Credentials");
        }
    }
}
