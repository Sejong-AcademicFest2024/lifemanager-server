package com.life_manager.life_manager.admin.domain.admin.dto;

import com.life_manager.life_manager.admin.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class AdminSignUpDto {
    @NotNull
    private String accountId;
    @NotNull
    private String password;
    @NotNull
    private String passwordCheck;
    @NotNull
    private String name;
    @NotNull
    private String companyName;
    @NotNull
    private String businessName;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String businessNumber;
    @NotNull
    private String category;
    @NotNull
    private Role role;

    public AdminDto toDto() {
        return new AdminDto(accountId, password, name,companyName, businessName, address, phoneNumber, businessNumber, category, role);
    }
}