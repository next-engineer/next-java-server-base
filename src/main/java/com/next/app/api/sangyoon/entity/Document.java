package com.next.app.api.sangyoon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`documents`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Document {
    @Id
    @Column(name = "`doc_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;

    @Column(name = "`title`", length = 200, nullable = false)
    private String title;

    @Column(name = "`owner_id`", nullable = false)
    private Long ownerId;

    @Column(name = "`file_path`", nullable = false, unique = true)
    private String filePath;

    @Builder.Default
    @Column(name = "`created_at`", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
