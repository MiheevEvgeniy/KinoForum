package com.MyProject.KinoForum.category.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class NewCategory {
    @Size(min = 1, max = 300)
    @NotBlank
    private String name;
}
