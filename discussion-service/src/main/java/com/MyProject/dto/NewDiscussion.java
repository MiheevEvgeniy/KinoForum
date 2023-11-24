package com.MyProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewDiscussion {
    @NotBlank
    @Size(min = 1, max = 1000)
    private String title;
    @Size(max = 3000)
    private String description;
    @NotNull
    private Long author;
    private List<Long> categories;
}
