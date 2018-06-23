package com.service.example.microservicedemo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailsRequest {

    @NotBlank(message = "{id.not-null}")
    @Size(min=2, max = 10, message = "{id.size}")
    private String id;

    @NotBlank(message = "{name.not-null}")
    private String name;

    @NotNull(message = "{mobile.not-null}")
    @Size(min=10, max=13, message = "{mobile.size}")
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="{mobile.pattern}")
    private String mobile;

    private String location;
}
