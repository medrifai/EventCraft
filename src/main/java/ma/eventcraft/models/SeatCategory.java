package ma.eventcraft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat_categories")
public class SeatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int maxNumberOfSeats;

    @Column(nullable = false)
    private int availableSeats;

    @OneToMany(mappedBy = "seatCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vector<Ticket> tickets;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
