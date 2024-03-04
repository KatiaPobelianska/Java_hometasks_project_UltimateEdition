package spring.hotel.booking.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.hotel.booking.app.model.Booking;
import spring.hotel.booking.app.model.Room;
import spring.hotel.booking.app.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }
    public Optional<Booking> getById(long id){
        return bookingRepository.findById(id);
    }
    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }
    public void deleteById(long id){
        bookingRepository.deleteById(id);
    }
    public List<Booking> getAllByRoom(Room room){
        return bookingRepository.findAllByRoom(room);
    }
}
