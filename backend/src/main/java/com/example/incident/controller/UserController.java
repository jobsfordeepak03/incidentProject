package com.example.incident.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.incident.dto.*;
import com.example.incident.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse me(Authentication auth) {
        return userService.me(auth.getName());
    }

    @GetMapping("/pincode/{pincode}")
    public PinCodeResponse pincode(@PathVariable String pincode) {
        return userService.lookup(pincode);
    }
}
