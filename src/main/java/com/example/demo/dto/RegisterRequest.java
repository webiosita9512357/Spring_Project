package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Username is mandatory")
    private String userName;
    @NotBlank(message = "Password is mandatory")
    private String password;

}
