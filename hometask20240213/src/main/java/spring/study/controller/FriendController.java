package spring.study.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.study.dao.FriendBirthdayDao;
import spring.study.model.Friend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendBirthdayDao friendBirthdayDao;

    @Autowired
    public FriendController(FriendBirthdayDao friendBirthdayDao) {
        this.friendBirthdayDao = friendBirthdayDao;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(friendBirthdayDao.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getById(@PathVariable("id") long id) {
        Friend friend = null;
        try {
            friend = friendBirthdayDao.getFriendById(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friend, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> save(@RequestBody @Valid Friend friend, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        friendBirthdayDao.save(friend);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/birthday")
    public ResponseEntity<List<Friend>> getAllByDate(@RequestParam("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday) {
        List<Friend> friends = new ArrayList<>();
        try {
            friends = friendBirthdayDao.getByDate(birthday);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @GetMapping("/fullname")
    public ResponseEntity<Friend> getByFullname(@RequestParam("firstName") String firstName,
                                                @RequestParam("lastName") String lastName) {
        Friend friend = null;
        try {
            friend = friendBirthdayDao.getByFullname(firstName, lastName);
            if (friend == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(friend, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Friend> delete(@PathVariable("id") long id){
        friendBirthdayDao.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}