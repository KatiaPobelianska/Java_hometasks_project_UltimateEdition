package spring.hotel.booking.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.hotel.booking.app.model.Booking;
import spring.hotel.booking.app.model.Room;
import spring.hotel.booking.app.repository.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(long id) {
        return roomRepository.findById(id).stream().findAny();
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void deleteById(long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllUnbooked(LocalDate start, LocalDate leave) {
        List<Room> allRooms = getAllRooms();
        List<Room> unbookedRooms = new ArrayList<>(allRooms);

        for (Room room : allRooms) {
            List<Booking> bookings = room.getBookings();
            for (Booking booking : bookings) {
                LocalDate bookingStartDate = booking.getDateStart();
                LocalDate bookingEndDate = booking.getDateLeave();

                if ((start.isAfter(bookingStartDate) && start.isBefore(bookingEndDate)) ||
                        (leave.isAfter(bookingStartDate) && leave.isBefore(bookingEndDate)) ||
                        (start.isBefore(bookingStartDate) && leave.isAfter(bookingEndDate))) {
                    unbookedRooms.remove(room);
                    break;
                }
            }
        }
        return unbookedRooms;
    }
}
