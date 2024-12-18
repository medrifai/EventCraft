package ma.eventcraft.model;

import java.util.Date;
import java.util.Vector;

public class Event {

    private int id;
    private String title;
    private String description;
    private String location;
    private User organizer;
    private EventCategory category;
    private Vector<SeatCategory> seatCategories;
    private Vector<Ticket> tickets;
    private Boolean isSelling;
    private Date startsAt;
    private Date endsAt;
    private Date createdAt;
    private Date updatedAt;

}
