package ma.eventcraft.model;

import java.util.Date;
import java.util.Vector;

public class EventCategory {

    private int id;
    private String name;
    private Vector<Event> events;
    private Date createdAt;
    private Date updatedAt;
}
