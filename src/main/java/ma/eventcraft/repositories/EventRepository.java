package ma.eventcraft.repositories;

import ma.eventcraft.models.Event;
import ma.eventcraft.models.EventCategory;
import ma.eventcraft.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByTitleAndStartsAt(String title, Date startsAt);
    List<Event> findByLocationContainingIgnoreCase(String location);

    List<Event> findByCategory(EventCategory category);

    List<Event> findByOrganizer(User organizer);

    List<Event> findByTitleContainingIgnoreCase(String keyword);

    List<Event> findByIsSellingTrue();

    @Query("SELECT e FROM Event e WHERE SIZE(e.tickets) < SIZE(e.seatCategories)")
    List<Event> findEventsWithAvailableSeats();

    // pour récupérer les événements avec une catégorie spécifique et un certain nombre de tickets
    @Query("SELECT e FROM Event e JOIN e.tickets t WHERE e.category = :category AND SIZE(e.tickets) > :minTickets")
    List<Event> findEventsByCategoryAndMinTickets(EventCategory category, int minTickets);

    @Query("SELECT e FROM Event e WHERE e.startsAt > CURRENT_TIMESTAMP AND e.isSelling = true")
    List<Event> findUpcomingEvents();


}
