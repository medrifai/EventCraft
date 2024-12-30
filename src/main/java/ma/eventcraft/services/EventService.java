package ma.eventcraft.services;
import ma.eventcraft.repositories.EventRepository;
import ma.eventcraft.models.Event;
import ma.eventcraft.models.EventCategory;
import ma.eventcraft.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {
    
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    
    @Autowired
    private EventRepository eventRepository;
    
    // Core methods for event details page
    public Event getEventById(Long id) {
        logger.info("Fetching event with ID: {}", id);
        
        try {
            return eventRepository.findEventWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error fetching event with ID: " + id, e);
            throw new RuntimeException("Error fetching event: " + e.getMessage());
        }
    }
    
    public List<Event> getSuggestedEvents(Event event, int limit) {
        return eventRepository.findByCategory(event.getCategory())
            .stream()
            .filter(e -> !e.getId().equals(event.getId()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    // Other useful service methods mapping to your repository methods
    public List<Event> findByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location);
    }
    
    public List<Event> findByCategory(EventCategory category) {
        return eventRepository.findByCategory(category);
    }
    
    public List<Event> findByOrganizer(User organizer) {
        return eventRepository.findByOrganizer(organizer);
    }
    
    public List<Event> searchByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Event> findSellingEvents() {
        return eventRepository.findByIsSellingTrue();
    }
    
    public List<Event> findEventsWithAvailableSeats() {
        return eventRepository.findEventsWithAvailableSeats();
    }
    
    public List<Event> findEventsByCategoryAndMinTickets(EventCategory category, int minTickets) {
        return eventRepository.findEventsByCategoryAndMinTickets(category, minTickets);
    }
    
    public List<Event> findUpcomingEvents() {
        return eventRepository.findUpcomingEvents();
    }
    
    public List<Event> getRelatedEvents(Long categoryId, Long currentEventId) {
        List<Event> events = eventRepository.findByCategoryIdAndIdNotOrderByStartsAtDesc(categoryId, currentEventId);
        
        // Limiter à 4 événements
        return events.stream()
                     .limit(4)  // Limite les événements à 4
                     .collect(Collectors.toList());
    }
}
