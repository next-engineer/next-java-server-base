package com.next.app.api.user.service;

import com.next.app.api.user.dto.LeaveRequestDTO;
import com.next.app.api.user.dto.LeaveResponseDTO;
import com.next.app.api.user.entity.Leave;
import com.next.app.exception.BusinessException;
import com.next.app.exception.ErrorCode;
import com.next.app.api.user.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveResponseDTO requestLeave(LeaveRequestDTO dto) {
        Leave leave = Leave.builder()
                .employeeId(dto.getEmployeeId())
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(Leave.LeaveStatus.REQUESTED)  // 대문자 REQUESTED로 변경
                .reason(dto.getReason())
                .build();

        if (leave.isExceedingMaxDuration()) {
            log.warn("휴가 신청이 15일을 초과했습니다. employeeId={}, start={}, end={}",
                    dto.getEmployeeId(), dto.getStartDate(), dto.getEndDate());
            throw new BusinessException(ErrorCode.LEAVE_DURATION_EXCEEDED);
        }

        Leave saved = leaveRepository.save(leave);
        return LeaveResponseDTO.fromEntity(saved);
    }
}
