package com.example.autheticationDemo.Entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String refreshToken;

    private Instant expireAt;



    @ManyToOne
    private User user;

    public RefreshToken() {
    }

    public RefreshToken(Instant expireAt, Integer id, String refreshToken, User user) {
        this.expireAt = expireAt;
        this.id = id;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public Instant getExpireAt() {
        return expireAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpireAt(Instant expireAt) {
        this.expireAt = expireAt;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
