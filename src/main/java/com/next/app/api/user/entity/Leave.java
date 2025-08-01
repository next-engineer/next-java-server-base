package com.next.app.api.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @Column(nullable = false)
    private Long employeeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType leaveType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status;

    @Column(length = 255)
    private String reason;

    public boolean isExceedingMaxDuration() {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return days > 15;
    }

    public enum LeaveType {
        ANNUAL, SICK, ETC
    }

    public enum LeaveStatus {
        REQUESTED, APPROVED, REJECTED
    }
}
