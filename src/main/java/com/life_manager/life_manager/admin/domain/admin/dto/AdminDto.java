package com.life_manager.life_manager.admin.domain.admin.dto;

import com.life_manager.life_manager.admin.Admin;
import com.life_manager.life_manager.admin.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@AllArgsConstructor
public class AdminDto {

    private String id;
    private String accountId;
    private String password;
    private String name;
    private String companyName;
    private String businessName;
    private String address;
    private String phoneNumber;
    private String businessNumber;

    private String category;

    private Role role;

    private List<Post> postList=new ArrayList<>();  //list<dto>로 바꿀것

    public AdminDto(String accountId, String password, String name, String companyName, String businessName,
                    String address, String phoneNumber, String businessNumber, String category, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.companyName = companyName;
        this.businessName = businessName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.category = category;
        this.role = role;
    }

    public AdminDto(String id, String accountId, String name, String businessName, String companyName, String address, String category, String phoneNumber, String businessNumber) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.businessName = businessName;
        this.companyName = companyName;
        this.address = address;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
    }

    public static AdminDto fromEntity(Admin admin) {
        return new AdminDto(
                admin.getId(),
                admin.getAccountId(),
                admin.getName(),
                admin.getBusinessName(),
                admin.getCompanyName(),
                admin.getAddress(),
                admin.getCategory(),
                admin.getPhoneNumber(),
                admin.getBusinessNumber()
        );
    }

    public Admin toEntity(String encryptedPassword){
        return new Admin(accountId, encryptedPassword, name, companyName, businessName, address, phoneNumber, businessNumber, category, role);
    }
}