package com.life_manager.life_manager.admin.domain.admin.controller;

import com.life_manager.life_manager.admin.domain.admin.dto.AdminDto;
import com.life_manager.life_manager.admin.domain.admin.dto.AdminSignInDto;
import com.life_manager.life_manager.admin.domain.admin.dto.AdminSignUpDto;
import com.life_manager.life_manager.admin.domain.admin.dto.SignInResponse;
import com.life_manager.life_manager.admin.domain.admin.service.AdminService;
import com.life_manager.life_manager.global.security.util.SecurityContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;
    @Operation(summary = "회원가입", description = "사업자(관리자)의 회원가입입니다")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> adminSignUp(@RequestBody AdminSignUpDto adminSignUpDto){
        adminService.signUp(adminSignUpDto.toDto());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "로그인", description = "사업자(관리자)의 로그인입니다")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse>adminSignIn(@RequestBody AdminSignInDto adminSignInDto){
        SignInResponse signInResponse = adminService.signIn(adminSignInDto);
        return ResponseEntity.ok()
                .body(new SignInResponse(signInResponse.getId(), signInResponse.getAccessToken()));
    }

    @Operation(summary = "내 정보 조회", description = "사업자(관리자)의 정보를 조회합니다")
    @GetMapping("/my-page")
    public ResponseEntity<AdminDto>getAdmin(){
        String adminId = SecurityContextUtil.extractAdminId();
        AdminDto adminDto = adminService.getAdmin(adminId);
        return ResponseEntity.ok()
                .body(adminDto);
    }
}