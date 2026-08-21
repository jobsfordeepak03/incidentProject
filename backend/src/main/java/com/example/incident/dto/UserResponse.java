package com.example.incident.dto;

import lombok.*;
@Data @Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String pincode;
    private String city;
    private String country;
}
