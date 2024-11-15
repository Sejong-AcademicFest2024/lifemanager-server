package com.life_manager.life_manager.user.domain.resume.controller;

import com.life_manager.life_manager.user.domain.resume.dto.*;
import com.life_manager.life_manager.user.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @Operation(summary = "이력서 정보 조회", description = "이력서 정보를 조회합니다.")
    @GetMapping("/info")
    public ResumeInfoResponse getResumeInfo() {
        String workerId = SecurityContextUtil.extractWorkerId();

        return resumeService.getResumeInfo(workerId);
    }

    @Operation(summary = "프로필 이미지 업로드", description = "이력서의 프로필 이미지를 업로드합니다.")
    @PutMapping(value = "/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadProfileImage(@RequestPart(value = "profileImage") MultipartFile profileImage) {
        String workerId = SecurityContextUtil.extractWorkerId();

        resumeService.uploadProfileImage(workerId, profileImage);
    }

    @Operation(summary = "프로필 이미지 URL 조회", description = "이력서의 프로필 이미지 URL을 조회합니다.")
    @GetMapping("/profile-image")
    public ProfileImageUrlResponse getProfileImageUploadedUrl() {
        String workerId = SecurityContextUtil.extractWorkerId();

        return resumeService.getProfileImageUploadedUrl(workerId);
    }

    @Operation(summary = "자격증 정보 수정", description = "이력서의 자격증 정보를 수정합니다.")
    @PutMapping("/certificate")
    public void updateCertificates(@RequestBody CertificateUpdateRequest request) {
        String workerId = SecurityContextUtil.extractWorkerId();

        resumeService.updateCertificates(request, workerId);
    }

    @Operation(summary = "한줄소개 수정", description = "이력서의 한줄소개를 수정합니다.")
    @PutMapping("/statement")
    public void updateStatement(@RequestBody StatementUpdateRequest request) {
        String workerId = SecurityContextUtil.extractWorkerId();

        resumeService.updateStatement(request, workerId);
    }

    @Operation(summary = "경력 수정", description = "이력서의 경력을 수정합니다.")
    @PutMapping("/career")
    public void updateCareer(@RequestBody List<CareerUpdateRequest> requests) {
        String workerId = SecurityContextUtil.extractWorkerId();

        resumeService.updateCareer(requests, workerId);
    }

    @Operation(summary = "스킬 수정", description = "이력서의 스킬을 수정합니다.")
    @PutMapping("/skill/{skillIds}")
    public void updateSkills(@PathVariable List<Integer> skillIds) {
        String workerId = SecurityContextUtil.extractWorkerId();

        resumeService.updateSkills(skillIds, workerId);
    }
}