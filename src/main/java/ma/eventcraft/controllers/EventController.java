package ma.eventcraft.controllers;

import ma.eventcraft.services.EventService;
import ma.eventcraft.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public String showEventDetails(@PathVariable Long id, Model model) {
        System.out.println("Entered function with ID: " + id); // Console log
        logger.info("Entered function with ID: {}", id);      // Logger info

        model.addAttribute("id", id); // Add ID to the model for display in the view

        Event event = eventService.getEventById(id);

            if (event == null) {
                logger.error("Event with ID {} not found", id);
                model.addAttribute("error", "Event not found");
                return "error"; // Return to an error page
            }

            // Fetch related events limited to 4
            List<Event> relatedEvents = eventService.getRelatedEvents(event.getCategory().getId(), id);
            if (relatedEvents == null || relatedEvents.isEmpty()) {
                logger.warn("No related events found for category ID {}", event.getCategory().getId());
                relatedEvents = new ArrayList<>(); // Avoid null pointer exceptions
            }

            if (event.getImageUrl() == null || event.getImageUrl().isEmpty()) {
                logger.warn("Event image URL is missing for event ID {}", id);
                model.addAttribute("error", "Image not available.");
            }
            
            if (event.getStartsAt() == null) {
                logger.warn("Event start date is missing for event ID {}", id);
                model.addAttribute("error", "Event start date not available.");
            }
            logger.info("Found {} related events", relatedEvents.size());

            logger.info("Successfully found event: {}", event.getTitle());
            logger.info("Event details - Location: {}, Start Date: {}", event.getLocation(), event.getStartsAt());

            model.addAttribute("event", event);
            model.addAttribute("relatedEvents", relatedEvents);

            logger.info("Event me: {}", event);
            logger.info("Related Events: {}", relatedEvents != null ? relatedEvents : "No related events found");

            

            return "event-details"; // Create a simple test view

        // try {
        //     logger.info("Attempting to fetch event with ID: {}", id);

        //     Event event = eventService.getEventById(id);

        //     if (event == null) {
        //         logger.error("Event with ID {} not found", id);
        //         model.addAttribute("error", "Event not found");
        //         return "error";
        //     }

        //     // Fetch related events limited to 4
        //     List<Event> relatedEvents = eventService.getRelatedEvents(event.getCategory().getId(), id);
        //     logger.info("Found {} related events", relatedEvents.size());

        //     logger.info("Successfully found event: {}", event.getTitle());
        //     logger.info("Event details - Location: {}, Start Date: {}", event.getLocation(), event.getStartsAt());

        //     model.addAttribute("event", event);
        //     model.addAttribute("relatedEvents", relatedEvents);
        //     return "event-details";

        // } catch (Exception e) {
        //     logger.error("Error fetching event with ID: " + id, e);
        //     model.addAttribute("error", "Event not found: " + e.getMessage());
        //     return "error";
        // }
    }
}
