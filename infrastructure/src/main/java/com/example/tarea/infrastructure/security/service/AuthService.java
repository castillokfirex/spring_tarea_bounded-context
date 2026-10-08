package com.example.tarea.infrastructure.security.service;

import com.example.tarea.infrastructure.security.dto.AuthRequest;
import com.example.tarea.infrastructure.security.dto.AuthResponse;
import com.example.tarea.infrastructure.security.dto.RegisterRequest;
import com.example.tarea.infrastructure.security.entity.AppUser;
import com.example.tarea.infrastructure.security.entity.Role;
import com.example.tarea.infrastructure.security.jwt.JwtService;
import com.example.tarea.infrastructure.security.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(AppUserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        var user = new AppUser(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
