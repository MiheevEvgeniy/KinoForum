package com.MyProject.KinoForum.category.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCategory {
    @Size(min = 1, max = 300, message = "name size must be between 1 and 300 letters")
    @NotBlank
    private String name;
}
