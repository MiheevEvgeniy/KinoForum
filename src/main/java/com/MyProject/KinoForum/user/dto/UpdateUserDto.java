package com.MyProject.KinoForum.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserDto {
    private String name;
    private String country;
}
