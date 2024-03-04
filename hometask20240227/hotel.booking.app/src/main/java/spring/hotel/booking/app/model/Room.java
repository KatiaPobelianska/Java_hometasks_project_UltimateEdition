package spring.hotel.booking.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    @Min(value = 1)
    private int number;
    @Column(name = "beds")
    @Min(value = 1)
    private int beds;
    @Column(name = "price")
    @Min(value = 1)
    private int price;
   @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Booking> bookings;
}
