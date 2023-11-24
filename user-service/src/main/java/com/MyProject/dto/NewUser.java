package com.MyProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewUser {
    @Size(min = 1, max = 300, message = "name size must be between 1 and 300 letters")
    @NotBlank
    private String name;
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "email must have the required pattern (email@mail.com)")
    @NotBlank
    private String email;
    @NotBlank
    private String country;
}
