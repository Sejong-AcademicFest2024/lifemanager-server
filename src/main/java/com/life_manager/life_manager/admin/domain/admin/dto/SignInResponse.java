package com.life_manager.life_manager.admin.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInResponse {
    String accessToken;
    String id;
}
