package com.next.app.api.user.dto;

import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.entity.Leave.LeaveType;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {
    private Long employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
