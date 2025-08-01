package com.next.app.api.user.controller;

import com.next.app.api.user.dto.LeaveRequestDTO;
import com.next.app.api.user.dto.LeaveResponseDTO;
import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // 모든 휴가 조회
    @GetMapping
    public List<LeaveResponseDTO> getAllLeaves() {
        return leaveService.getAllLeaves().stream()
                .map(LeaveResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 특정 휴가 조회
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(@PathVariable("id") Long id) {
        Optional<Leave> leaveOpt = leaveService.getLeaveById(id);
        return leaveOpt
                .map(leave -> ResponseEntity.ok(LeaveResponseDTO.fromEntity(leave)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 휴가 추가
    @PostMapping
    public ResponseEntity<LeaveResponseDTO> createLeave(@Valid @RequestBody LeaveRequestDTO leaveRequestDTO) {
        Leave leave = leaveService.saveLeave(leaveRequestDTO);
        LeaveResponseDTO responseDTO = LeaveResponseDTO.fromEntity(leave);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 휴가 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable("id") Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
