package com.firstassignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberSignupRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*@[a-zA-Z]+\\.(com|net|org)$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank
    private String password;
}
