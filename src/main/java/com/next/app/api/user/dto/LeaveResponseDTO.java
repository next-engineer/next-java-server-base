package com.next.app.api.user.dto;

import com.next.app.api.user.entity.Leave.LeaveType;
import com.next.app.api.user.entity.Leave.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponseDTO {

    private Long leaveId;
    private Long employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;
    private String reason;

    // Entity -> DTO 변환 팩토리 메서드
    public static LeaveResponseDTO fromEntity(com.next.app.api.user.entity.Leave leave) {
        return new LeaveResponseDTO(
                leave.getId(),
                leave.getEmployeeId(),
                leave.getLeaveType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getStatus(),
                leave.getReason()
        );
    }
}
