package ma.eventcraft.model;

import java.util.Date;
import java.util.Vector;

public class SeatCategory {

    private int id;
    private String name;
    private Event event;
    private float price;
    private int maxNumberOfSeats;
    private int availableSeats;
    private Vector<Ticket> tickets;
    private Date createdAt;
    private Date updatedAt;
}
