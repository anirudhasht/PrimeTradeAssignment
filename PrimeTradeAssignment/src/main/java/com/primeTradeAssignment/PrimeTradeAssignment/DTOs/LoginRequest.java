package com.primeTradeAssignment.PrimeTradeAssignment.DTOs;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}