package com.example.employeeSpring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    // Getters and setters...
}