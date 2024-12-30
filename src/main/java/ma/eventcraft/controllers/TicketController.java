package ma.eventcraft.controllers;

import ma.eventcraft.models.Ticket;
import ma.eventcraft.repositories.TicketRepository;
import ma.eventcraft.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@Controller
public class TicketController {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private TicketService ticketService;

    // Existing endpoint for showing tickets page
    @GetMapping("/my-ticket-page")
public String showTickets(Model model) {
    try {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // Gets logged-in user's email
        List<Ticket> tickets = ticketRepository.findByOwnerEmail(email);
        model.addAttribute("tickets", tickets);
        return "my-ticket-page";
    } catch (Exception e) {
        model.addAttribute("error", "An error occurred while fetching tickets");
        return "error"; 
    }
}

    // New endpoint for getting ticket details
    @GetMapping("/api/tickets/{id}")
    @ResponseBody
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable Long id) {
        try {
            Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // New endpoint for cancelling a ticket
    @PostMapping("/api/tickets/{id}/cancel")
    @ResponseBody
    public ResponseEntity<Ticket> cancelTicket(@PathVariable Long id) {
        try {
            Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
            ticket.setStatus("Cancelled");
            ticket = ticketRepository.save(ticket);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}