package com.example.autheticationDemo.DTO;

public class RegisterRequestDTO {
    private String username;
    private String password;


    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
