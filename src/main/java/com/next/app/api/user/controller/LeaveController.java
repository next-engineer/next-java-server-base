package com.next.app.api.user.controller;

import com.next.app.api.user.dto.LeaveRequestDTO;
import com.next.app.api.user.dto.LeaveResponseDTO;
import com.next.app.api.user.entity.Leave;
import com.next.app.api.user.service.LeaveService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;  // 이걸 추가해야 함
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaves")
@Slf4j  // 여기에 붙임
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // 모든 휴가 조회
    @GetMapping
    public List<LeaveResponseDTO> getAllLeaves() {
        log.info("모든 휴가 조회 요청");  // 로그 추가
        return leaveService.getAllLeaves().stream()
                .map(LeaveResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 특정 휴가 조회
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(@PathVariable("id") Long id) {
        log.info("휴가 조회 요청 - ID: {}", id);  // 로그 추가
        Optional<Leave> leaveOpt = leaveService.getLeaveById(id);
        return leaveOpt
                .map(leave -> ResponseEntity.ok(LeaveResponseDTO.fromEntity(leave)))
                .orElseGet(() -> {
                    log.warn("휴가 ID {} 를 찾을 수 없음", id);  // 로그 경고 추가
                    return ResponseEntity.notFound().build();
                });
    }

    // 휴가 추가
    @PostMapping
    public ResponseEntity<LeaveResponseDTO> createLeave(@Valid @RequestBody LeaveRequestDTO leaveRequestDTO) {
        log.info("휴가 추가 요청: {}", leaveRequestDTO);
        Leave leave = leaveService.saveLeave(leaveRequestDTO);
        LeaveResponseDTO responseDTO = LeaveResponseDTO.fromEntity(leave);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 휴가 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable("id") Long id) {
        log.info("휴가 삭제 요청 - ID: {}", id);
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
