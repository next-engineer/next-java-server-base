package com.next.app.api.user.dto;

import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.entity.Leave.LeaveStatus;
import com.next.app.api.user.entity.Leave.LeaveType;
import lombok.*;

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

    public static LeaveResponseDTO fromEntity(Leave leave) {
        return new LeaveResponseDTO(
                leave.getLeaveId(),
                leave.getEmployeeId(),
                leave.getLeaveType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getStatus(),
                leave.getReason()
        );
    }
}
