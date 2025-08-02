package com.next.app.api.user.service;

import com.next.app.api.user.entity.Ticket;
import com.next.app.api.user.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long ticketId, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == Ticket.Status.closed) {
            throw new RuntimeException("Ticket status is closed");
        }

        ticket.setTitle(ticket.getTitle());
        ticket.setDescription(ticket.getDescription());
        ticket.setStatus(ticket.getStatus());
        ticket.setPriority(ticket.getPriority());

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
