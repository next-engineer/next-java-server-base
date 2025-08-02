package com.next.app.api.user.service;

import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.repository.LeaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveService.class);

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getAllLeaves() {
        logger.info("Fetching all leave records...");
        return leaveRepository.findAll();
    }

    public Leave createLeave(Leave leave) {
        logger.info("Creating leave for employeeId={} from {} to {}",
                leave.getEmployeeId(), leave.getStartDate(), leave.getEndDate());

        long days = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
        if (days > 15) {
            logger.warn("Leave duration exceeds 15 days: {} days", days);
            throw new IllegalArgumentException("휴가는 최대 15일까지만 가능합니다.");
        }

        Leave saved = leaveRepository.save(leave);
        logger.info("Leave created with ID: {}", saved.getLeaveId());
        return saved;
    }
}
