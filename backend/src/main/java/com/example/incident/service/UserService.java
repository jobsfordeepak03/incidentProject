package com.example.incident.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import com.example.incident.dto.*;
import com.example.incident.exception.BusinessException;
import com.example.incident.exception.ResourceNotFoundException;
import com.example.incident.repository.UserRepository;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    private final RestTemplate restTemplate;

    public UserResponse me(String email) {
        var u = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserResponse.builder().id(u.getId()).username(u.getUsername())
                .email(u.getEmail()).phone(u.getPhone()).address(u.getAddress())
                .pincode(u.getPincode()).city(u.getCity()).country(u.getCountry()).build();
    }

    public PinCodeResponse lookup(String pincode) {
    	
    	
    	 
    	if (pincode == null || !pincode.matches("\\d{6}")) {
            throw new BusinessException("PIN code must contain 6 digits");
        }

        String url = "https://api.postalpincode.in/pincode/" + pincode;

        try {
            ResponseEntity<PinCodeApiResponse[]> entity =
                    restTemplate.getForEntity(
                            url,
                            PinCodeApiResponse[].class,
                            pincode
                    );

            PinCodeApiResponse[] response = entity.getBody();

            if (response == null || response.length == 0) {
                throw new BusinessException(
                        "No response received for PIN code: " + pincode
                );
            }

            PinCodeApiResponse apiResponse = response[0];

            if (!"Success".equalsIgnoreCase(apiResponse.getStatus())
                    || apiResponse.getPostOffice() == null
                    || apiResponse.getPostOffice().isEmpty()) {

                throw new BusinessException(
                        apiResponse.getMessage() != null
                                ? apiResponse.getMessage()
                                : "Invalid PIN code: " + pincode
                );
            }

            PostOffice postOffice = apiResponse.getPostOffice().get(0);

            return new PinCodeResponse(
                    pincode,
                    postOffice.getDistrict(),
                    postOffice.getCountry(),
                    postOffice.getState()
            );

        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException(
                    "Unable to fetch location for PIN code: " + ex.getMessage()
            );
        }
    }
}
