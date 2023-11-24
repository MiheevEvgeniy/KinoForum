package com.MyProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCategory {
    @Size(min = 1, max = 300, message = "name size must be between 1 and 300 letters")
    @NotBlank
    private String name;
}
