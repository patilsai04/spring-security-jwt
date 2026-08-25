package com.example.autheticationDemo.Repository;

import com.example.autheticationDemo.Entity.RefreshToken;
import com.example.autheticationDemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    void deleteByUser(User user);
}
