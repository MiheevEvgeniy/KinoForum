package com.MyProject.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewDirector {
    @Size(min = 1, max = 300)
    @NotBlank
    private String name;
}
