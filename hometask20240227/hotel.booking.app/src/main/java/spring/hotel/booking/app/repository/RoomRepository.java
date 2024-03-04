package spring.hotel.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.hotel.booking.app.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
