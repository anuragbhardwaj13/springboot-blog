package dev.anurag.blogapp.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String identifier;
    private String password;

}
