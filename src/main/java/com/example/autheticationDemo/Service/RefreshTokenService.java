package com.example.autheticationDemo.Service;

import com.example.autheticationDemo.DTO.LoginResponseDTO;
import com.example.autheticationDemo.Entity.RefreshToken;
import com.example.autheticationDemo.Entity.User;
import com.example.autheticationDemo.Repository.RefreshTokenRepository;
import com.example.autheticationDemo.Security.JwtService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken generateRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpireAt(
                Instant.now().plus(30, ChronoUnit.DAYS)
        );
        return refreshTokenRepository.save(refreshToken);
    }
    // check the token is expired or no
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpireAt().isBefore(Instant.now())){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("RefreshToken expired");
        }
        return refreshToken;
    }
    // complete refreshaccessToke
    public LoginResponseDTO refreshAccessToken(String token){
        RefreshToken oldToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(()-> new RuntimeException("refresh token not found"));
        verifyExpiration(oldToken);
        User user = oldToken.getUser();
        //invalidate the old refreshToken
        RefreshToken newRefreshToken = generateRefreshToken(user);

        // create the new accessToken

        String newAccessToken = jwtService.generateToken(user.getUsername());
        return new LoginResponseDTO(
                newAccessToken,
                newRefreshToken.getRefreshToken()
        );
    }
    public void deleteByToken(String token){
        RefreshToken refreshToken =
                refreshTokenRepository.findByRefreshToken(token)
                        .orElseThrow(()->new RuntimeException("refresh token not found"));
       refreshTokenRepository.delete(refreshToken);
    }
}
