package com.next.app.api.user.controller;

import com.next.app.api.user.entity.Ticket;
import com.next.app.api.user.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Controller", description = "티켓 관리 API")
@CrossOrigin(origins = "http://localhost")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    @Operation(summary = "모든 티켓 조회", description = "등록된 모든 티켓 목록을 반환합니다.")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    @Operation(summary = "티켓 조회", description = "TicketID로 특정 티켓을 조회합니다.")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long ticketId) {
        return ticketService.getTicketById(ticketId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "티켓 생성", description = "새로운 티켓을 생성합니다.")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{ticketId}")
    @Operation(summary = "티켓 수정", description = "기존 티켓 정보를 수정합니다.")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody Ticket ticketDetails) {
        try {
            Ticket updateTicket = ticketService.updateTicket(ticketId, ticketDetails);
            return ResponseEntity.ok(updateTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "티켓 삭제", description = "티켓을 삭제합니다.")
    public ResponseEntity<Void>  deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok().build();
    }
}
