package com.next.app.api.user.controller;

import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PostMapping
    public Leave createLeave(@RequestBody Leave leave) {
        return leaveService.createLeave(leave);
    }
}
