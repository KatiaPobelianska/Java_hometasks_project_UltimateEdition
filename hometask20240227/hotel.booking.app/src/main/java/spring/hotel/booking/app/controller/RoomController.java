package spring.hotel.booking.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.hotel.booking.app.model.Room;
import spring.hotel.booking.app.service.RoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable("id") long id){
        Optional<Room> room = roomService.getById(id);
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room.get(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody Room room, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(roomService.save(room), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        roomService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/unbooked")
    public ResponseEntity<List<Room>> getUnbookedRooms(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("leave") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate leave) {

        List<Room> unbookedRooms = roomService.getAllUnbooked(start, leave);
        return new ResponseEntity<>(unbookedRooms, HttpStatus.OK);
    }
}


