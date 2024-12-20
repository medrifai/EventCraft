package ma.eventcraft.repositories;

import ma.eventcraft.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByEventId(Long eventId);
    List<Ticket> findBySeatCategoryId(Long seatCategoryId);

    @Query("SELECT t FROM Ticket t WHERE t.event.startsAt BETWEEN :startDate AND :endDate")
    List<Ticket> findTicketsForEventsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId AND t.seatCategory.id = :categoryId")
    Long countTicketsForEventAndCategory(Long eventId, Long categoryId);
}

