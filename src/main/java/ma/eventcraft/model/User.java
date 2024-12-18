package ma.eventcraft.model;

import ma.eventcraft.model.enums.Role;

import java.util.Date;
import java.util.Vector;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Vector<Role> roles;
    private Vector<Ticket> tickets;
    private Vector<Event> events;
    private Date createdAt;
    private Date updatedAt;

}
