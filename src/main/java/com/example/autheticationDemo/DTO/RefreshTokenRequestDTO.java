package com.example.autheticationDemo.DTO;

public class RefreshTokenRequestDTO {

    private String refreshToken;

    public RefreshTokenRequestDTO(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequestDTO() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
