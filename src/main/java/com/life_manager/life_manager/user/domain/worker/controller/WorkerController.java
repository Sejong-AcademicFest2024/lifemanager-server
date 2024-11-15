package com.life_manager.life_manager.user.domain.worker.controller;

import com.life_manager.life_manager.user.domain.worker.dto.WorkerApplicationsStatusResponse;
import com.life_manager.life_manager.user.domain.worker.dto.WorkerSignUpRequest;
import com.life_manager.life_manager.user.domain.worker.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class WorkerController {
    private final WorkerService workerService;

    @Operation(summary = "회원가입", description = "일반 사용자 회원가입을 진행합니다.")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody WorkerSignUpRequest request, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();

        workerService.signUp(request, session);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 지원 상태 조회
    @Operation(summary = "지원 상태 조회", description = "일반 사용자가 지원한 상태를 조회합니다.")
    @Parameter(name = "statuses", description = "PENDING(진행 중), EXPIRED(만료됨), CANCEL(취소함), ACCEPTED(수락됨), REJECTED(거절됨)", required = true)
    @GetMapping("/application/status")
    public List<WorkerApplicationsStatusResponse> findApplicationsStatuses(
            @RequestParam List<ApplicationStatus> statuses) {
        return workerService.findApplicationStatuses(statuses);
    }
}
