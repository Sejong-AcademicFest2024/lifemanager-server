package com.life_manager.life_manager.admin.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminSignInDto {
    @NotBlank String accountId;
    @NotBlank String password;
}