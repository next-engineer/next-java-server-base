
package com.next.app.api.user.controller;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
class usercontroller_ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    @Column(length = 10, unique = true)
    private String email;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    public enum Status {
        active
    }

    // 기본 생성자 (필수)
    public usercontroller_() {}

    // 생성자 및 getter/setter 필요 시 추가
}
