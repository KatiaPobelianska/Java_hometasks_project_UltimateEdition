package spring.hotel.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.hotel.booking.app.model.Booking;
import spring.hotel.booking.app.model.Room;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByRoom(Room room);
}
