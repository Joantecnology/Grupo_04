package com.digitalbooking.Back.repository;

import com.digitalbooking.Back.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReservationByUserId(Integer userId);
    List<Reservation> findReservationByProductId(Integer productId);
}
