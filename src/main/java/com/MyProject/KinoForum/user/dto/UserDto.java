package com.MyProject.KinoForum.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String country;
}
