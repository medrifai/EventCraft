package ma.eventcraft.services;

import ma.eventcraft.models.Ticket;
import ma.eventcraft.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

   // public Ticket cancelTicket(Long id) {
    //    Ticket ticket = getTicket(id);
     //   ticket.setStatus("Cancelled");
     //   return ticketRepository.save(ticket);
   // }
    
}