package com.example.incident.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank @Size(max = 120)
    private String username;

    @NotBlank @Email @Size(max = 180)
    private String email;

    @NotBlank @Size(max = 30)
    private String phone;

    @NotBlank @Size(max = 500)
    private String address;

    @NotBlank @Pattern(regexp = "\\d{6}", message = "PIN code must contain 6 digits")
    private String pincode;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank @Size(min = 8, max = 100)
    private String password;
}
