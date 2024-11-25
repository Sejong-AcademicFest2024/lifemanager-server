package com.life_manager.life_manager.admin.domain.worker;

import com.life_manager.life_manager.admin.domain.workerApplicationStatus.WorkerApplicationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Worker {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String memberAccount;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(columnDefinition = "char(11)", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @OneToMany(mappedBy = "worker")
    private List<WorkerApplicationStatus> workerApplicationStatusList = new ArrayList<>();
    @OneToMany(mappedBy = "worker")
    private List<Employee> employeeList = new ArrayList<>();

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Resume resume;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum Gender {
        MALE("남성"),
        FEMALE("여성");

        private final String name;
    }


    public void addWorkerApplicationStatus(WorkerApplicationStatus workerApplicationStatus) {
        workerApplicationStatusList.add(workerApplicationStatus);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}