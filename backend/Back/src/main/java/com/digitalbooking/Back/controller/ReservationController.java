package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.Reservation;
import com.digitalbooking.Back.model.User;
import com.digitalbooking.Back.repository.UserRepository;
import com.digitalbooking.Back.services.serviceInterface.ProductService;
import com.digitalbooking.Back.services.serviceInterface.ReservationService;
import io.swagger.annotations.Api;
/*import jakarta.transaction.Transactional;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation){
        User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To book you must enter your account, otherwise you must register");
        }else {
            return ResponseEntity.ok(reservationService.createReservation(reservation));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservationById(@PathVariable Integer id){
        Optional<Reservation> searchedReservation = reservationService.findReservationById(id);

        if (searchedReservation.isPresent()){
            return ResponseEntity.ok(searchedReservation.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> listReservation(){
        return ResponseEntity.ok(reservationService.listReservation());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reservation>> listByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findReservationByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Reservation>> listByProductId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findReservationByProductId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation){
        Optional<Reservation> searchedReservation = reservationService.findReservationById(reservation.getId());

        if (searchedReservation.isPresent()){
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("reservation with id"+reservation.getId()+ " updated");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Reservation with Id: "+reservation.getId()+" is not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation (@PathVariable("id") Integer id){
        if (reservationService.findReservationById(id).isPresent()){
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok("The Reservation with Id "+id+" has been remove");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Reservation with Id "+id+" is not found");
        }
    }
}
