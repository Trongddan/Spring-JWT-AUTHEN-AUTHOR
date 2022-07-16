package com.example.jwt3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor

public class AuthResponse {
    private String email;
    private String accessToken;
    private Collection roles;

    public AuthResponse(String email, String accessToken, Collection roles) {
        this.email = email;
        this.accessToken = accessToken;
        this.roles = roles;
    }
}
