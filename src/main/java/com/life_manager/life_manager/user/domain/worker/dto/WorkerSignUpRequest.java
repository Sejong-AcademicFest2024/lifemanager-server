package com.life_manager.life_manager.user.domain.worker.dto;

import com.life_manager.life_manager.user.domain.worker.Gender;
import java.time.LocalDate;

public record WorkerSignUpRequest(
        String memberAccount, String password, String passwordCheck, String name, String phoneNumber, LocalDate birthdate, String nickname, Gender gender, String address, Integer skillId
) {
}