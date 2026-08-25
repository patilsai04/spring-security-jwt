package com.example.autheticationDemo.Controller;

import com.example.autheticationDemo.DTO.*;
import com.example.autheticationDemo.Service.AuthService;
import com.example.autheticationDemo.Service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO){
        LoginResponseDTO token = authService.login(requestDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO requestDTO){
        authService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Register SuccessFully Done");
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO requestDTO){
        LoginResponseDTO response = refreshTokenService.refreshAccessToken(requestDTO.getRefreshToken());
        return ResponseEntity.ok(
                response
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequestDTO requestDTO){
        refreshTokenService.deleteByToken(requestDTO.getRefreshToken());
        return ResponseEntity.ok("Logout Successfully");
    }
}
