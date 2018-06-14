package com.service.example.microservicedemo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailsRequest {
    @NotNull(message = "id is required")
    @Size(min=2, max = 10, message = "id should be between 2 to 10 characters")
    private String id;
    private String name;
    private String location;
}
