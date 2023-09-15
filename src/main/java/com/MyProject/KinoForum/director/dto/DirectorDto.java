package com.MyProject.KinoForum.director.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DirectorDto {
    private Long id;
    private String name;
}
