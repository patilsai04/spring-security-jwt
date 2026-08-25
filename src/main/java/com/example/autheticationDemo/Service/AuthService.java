package com.example.autheticationDemo.Service;

import com.example.autheticationDemo.Config.SecurityConfig;
import com.example.autheticationDemo.DTO.LoginRequestDTO;
import com.example.autheticationDemo.DTO.LoginResponseDTO;
import com.example.autheticationDemo.DTO.RegisterRequestDTO;
import com.example.autheticationDemo.Entity.RefreshToken;
import com.example.autheticationDemo.Entity.Role;
import com.example.autheticationDemo.Entity.User;
import com.example.autheticationDemo.Repository.RoleRepository;
import com.example.autheticationDemo.Repository.UserRepository;
import com.example.autheticationDemo.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       requestDTO.getUsername(),
                       requestDTO.getPassword()
               )
        );
        String accessToken = jwtService.generateToken(authentication.getName());

        //find user for attaching the user name
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
        return new  LoginResponseDTO(
                accessToken,
                refreshToken.getRefreshToken()
        );
    }

    public User register(RegisterRequestDTO requestDTO){
        Role role = roleRepository.findByName("USER")
                .orElseThrow(()-> new UsernameNotFoundException("user role not found"));

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(role);

        return userRepository.save(user);
    }
}
