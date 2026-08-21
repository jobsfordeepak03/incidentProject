package com.example.incident.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostOffice {
	
	 @JsonProperty("Name")
	    private String name;

	    @JsonProperty("District")
	    private String district;

	    @JsonProperty("State")
	    private String state;

	    @JsonProperty("Country")
	    private String country;

	   
	    

}
