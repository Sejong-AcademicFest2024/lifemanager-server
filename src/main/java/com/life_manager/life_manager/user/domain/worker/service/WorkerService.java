package com.life_manager.life_manager.user.domain.worker.service;

import com.life_manager.life_manager.global.exception.CustomException;
import com.life_manager.life_manager.user.domain.auth.service.AuthService;
import com.life_manager.life_manager.user.domain.worker.Worker;
import com.life_manager.life_manager.user.domain.worker.dto.WorkerApplicationsStatusResponse;
import com.life_manager.life_manager.user.domain.worker.dto.WorkerSignUpRequest;
import com.life_manager.life_manager.user.domain.worker.relation.ApplicationStatus;
import com.life_manager.life_manager.user.domain.worker.relation.WorkerApplicationStatus;
import com.life_manager.life_manager.user.domain.worker.repository.WorkerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkerService {
    private final AuthService authService;

    private final WorkerRepository workerRepository;
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(WorkerSignUpRequest request, HttpSession session) {
        validateEnableSignUp(request, session);

        String encryptedPassword = passwordEncoder.encode(request.password());

        Worker worker = Worker.create(request, encryptedPassword);
        workerRepository.save(worker);

        Resume resume = Resume.create(worker);
        resumeRepository.save(resume);

        Skill skill = skillRepository.findById(request.skillId())
                .orElseThrow(() -> new CustomException(INVALID_SKILL_ID));
        resume.addSkills(skill);
    }

    private void validateEnableSignUp(WorkerSignUpRequest request, HttpSession session) {
        //authService.validateVerificationSession(session);

        if (workerRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new CustomException(DUPLICATED_PHONE_NUMBER);
        }

        if (workerRepository.existsByMemberAccount(request.memberAccount())) {
            throw new CustomException(DUPLICATED_MEMBER_ACCOUNT);
        }

        if (workerRepository.existsByNickname(request.nickname())) {
            throw new CustomException(DUPLICATED_NICKNAME);
        }
    }

    public List<WorkerApplicationsStatusResponse> findApplicationStatuses(List<ApplicationStatus> statuses) {
        String workerId = SecurityContextUtil.extractWorkerId();
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        List<WorkerApplicationStatus> workerApplicationStatuses = worker.getWorkerApplicationStatuses();

        renewApplicationStatus(workerApplicationStatuses);

        return workerApplicationStatuses.stream()
                .filter(status -> statuses.contains(status.getStatus()))
                .map(WorkerApplicationsStatusResponse::from)
                .toList();
    }

    private void renewApplicationStatus(List<WorkerApplicationStatus> workerApplicationStatuses) {
        // 해당 worker가 신청한 post 중, pending인 것만 전부 가져와, post가 이미 닫힌 경우 expired로 변경
        workerApplicationStatuses.stream()
                .filter(status -> status.getStatus() == ApplicationStatus.PENDING)
                .filter(status -> status.getPost().isClosed())
                .forEach(status -> status.updateStatus(ApplicationStatus.EXPIRED));
    }
}