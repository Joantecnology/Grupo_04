package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation createReservation (Reservation reservation);
    Optional <Reservation> findReservationById (Integer id);
    List<Reservation> listReservation();
    List<Reservation> findReservationByUserId(Integer userId);
    List<Reservation> findReservationByProductId(Integer productId);
    Reservation updateReservation (Reservation reservation);
    void deleteReservationById (Integer reservation);

}
