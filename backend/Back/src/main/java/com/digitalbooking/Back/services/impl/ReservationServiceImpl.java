package com.digitalbooking.Back.services.impl;

import com.digitalbooking.Back.model.Reservation;
import com.digitalbooking.Back.repository.ReservationRepository;
import com.digitalbooking.Back.repository.UserRepository;
import com.digitalbooking.Back.services.serviceInterface.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findReservationById(Integer id) {

        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> listReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findReservationByUserId(Integer userId) {
        return reservationRepository.findReservationByUserId(userId);
    }

    @Override
    public List<Reservation> findReservationByProductId(Integer productId) {
        return reservationRepository.findReservationByProductId(productId);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
