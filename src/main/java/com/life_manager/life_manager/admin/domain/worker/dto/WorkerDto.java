package com.life_manager.life_manager.admin.domain.worker.dto;

import com.life_manager.life_manager.admin.domain.worker.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WorkerDto {
    private String id;
    private String name;
    private LocalDate birthdate;
    private String nickname;
    private Worker.Gender gender;
    private String uploadUrl;

    public static WorkerDto FromEntityForWorkerApplicationStatusDto(Worker worker) {
        return new WorkerDto(
                worker.getId(),
                worker.getName(),
                worker.getBirthdate(),
                worker.getNickname(),
                worker.getGender(),
                worker.getResume().getUploadedUrl()
        );
    }
}
