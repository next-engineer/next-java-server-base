package com.next.app.api.user.service;

import com.next.app.api.user.dto.LeaveRequestDTO;
import com.next.app.api.user.entity.Leave;
import com.next.app.exception.BusinessException;
import com.next.app.api.user.repository.LeaveRepository;
import com.next.app.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // 모든 휴가 조회
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // 특정 휴가 조회 (id로)
    public Optional<Leave> getLeaveById(Long leaveId) {
        return leaveRepository.findById(leaveId);
    }

    // 휴가 저장 (추가 또는 수정) — DTO 받도록 변경, 15일 제한 체크 포함
    public Leave saveLeave(LeaveRequestDTO leaveRequestDTO) {
        // 연속된 휴가 일수 계산 (종료일 포함)
        long days = ChronoUnit.DAYS.between(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate()) + 1;
        if (days > 15) {
            throw new BusinessException(ErrorCode.LEAVE_DURATION_EXCEEDED);
        }

        Leave leave = new Leave();
        leave.setEmployeeId(leaveRequestDTO.getEmployeeId());
        leave.setLeaveType(leaveRequestDTO.getLeaveType());
        leave.setStartDate(leaveRequestDTO.getStartDate());
        leave.setEndDate(leaveRequestDTO.getEndDate());
        leave.setStatus(Leave.LeaveStatus.REQUESTED);  // 새로 신청하면 기본 상태는 REQUESTED
        leave.setReason(leaveRequestDTO.getReason());

        return leaveRepository.save(leave);
    }

    // 휴가 삭제
    public void deleteLeave(Long leaveId) {
        leaveRepository.deleteById(leaveId);
    }
}
