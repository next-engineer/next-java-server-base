package com.next.app.api.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Comment("프로젝트명")
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Comment("설명")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Comment("시작일")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Comment("종료일")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Comment("상태")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PLANNED,
        ONGOING,
        COMPLETED
    }

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 합니다.");
        }
    }
}
