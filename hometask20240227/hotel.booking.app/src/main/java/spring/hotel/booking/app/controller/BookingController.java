package spring.hotel.booking.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.hotel.booking.app.model.Booking;
import spring.hotel.booking.app.model.Room;
import spring.hotel.booking.app.service.BookingService;
import spring.hotel.booking.app.service.RoomService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;

    @Autowired
    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }
    @GetMapping()
    public ResponseEntity<List<Booking>> getAll() {
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable("id") long id){
        Optional<Booking> booking = bookingService.getById(id);
        if (booking.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booking.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.save(booking), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        bookingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byRoom/{roomId}")
    public ResponseEntity<List<Booking>> getAllByRoom(@PathVariable("room_id") long roomId) {
        Optional<Room> room = roomService.getById(roomId);
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Booking> bookings = bookingService.getAllByRoom(room.get());
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
