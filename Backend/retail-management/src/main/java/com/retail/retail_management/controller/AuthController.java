package com.retail.retail_management.controller;

import com.retail.retail_management.entity.User;
import com.retail.retail_management.security.JwtService;
import com.retail.retail_management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            JwtService jwtService) {

        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.register(
                        request.name(),
                        request.email(),
                        request.password()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        User user = authService.login(
                request.email(),
                request.password()
        );

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole().name()
                )
        );
    }

    public record RegisterRequest(
            String name,
            String email,
            String password
    ) {}

    public record LoginRequest(
            String email,
            String password
    ) {}

    public record LoginResponse(
            String token,
            Long id,
            String name,
            String email,
            String role
    ) {}
}