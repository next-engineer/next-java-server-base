package com.next.app.api.user.repository;

import com.next.app.api.user.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    // 필요 시 커스텀 쿼리 작성
}
