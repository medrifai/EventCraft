package ma.eventcraft.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.eventcraft.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByOwnerId(Long ownerId);
    List<Ticket> findByEventId(Long eventId);
    List<Ticket> findBySeatCategoryId(Long seatCategoryId);

    @Query("SELECT t FROM Ticket t WHERE t.event.startsAt BETWEEN :startDate AND :endDate")
    List<Ticket> findTicketsForEventsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId AND t.seatCategory.id = :categoryId")
    Long countTicketsForEventAndCategory(Long eventId, Long categoryId);
}

