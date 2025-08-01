package com.next.app.api.user.controller;

import com.next.app.api.user.dto.LeaveRequestDTO;
import com.next.app.api.user.dto.LeaveResponseDTO;
import com.next.app.api.user.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping
    public ResponseEntity<LeaveResponseDTO> requestLeave(@RequestBody LeaveRequestDTO dto) {
        LeaveResponseDTO response = leaveService.requestLeave(dto);
        return ResponseEntity.ok(response);
    }
}
