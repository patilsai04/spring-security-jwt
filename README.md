# spring-security-jwt
# Spring Security + JWT Authentication

## 📌 Overview
This project demonstrates **authentication and authorization** using **Spring Security** and **JWT (JSON Web Token)**.  
It includes secure login, role-based access control, and token validation for REST APIs.

## ⚙️ Tech Stack
- Java 17
- Spring Boot
- Spring Security
- JWT
- JPA/Hibernate
- MySQL/PostgreSQL

## 🚀 Features
- User signup & login with JWT token generation
- Role-based authorization (Admin, User)
- Secure REST endpoints with `@PreAuthorize`
- Token validation filter for every request
- Refresh token support for long-lived sessions

## Example -
POST /auth/register
{
  "username":"sai@gmail.com",
  "password":"sai123"
}
successfully register...

Post /auth/login
{
  "username":"sai@gmail.com",
  "password":"sai123"
}
-----
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWlAZ21haWwuY29tIiwiaWF0IjoxNzg3NjM0ODI2LCJleHAiOjE3ODc2MzU3MjZ9.yg4Go1LLsSDJwH1Ew5y15pt54fwIWnx7wcOVKRcNAOQ",
  "refreshToken": "71b9ee84-a971-4f45-aea8-e14cd33117a1"
}

## 🛠️ Setup
1. Clone the repo:
   ```bash
   git clone https://github.com/patilsai04/spring-security-jwt.git
