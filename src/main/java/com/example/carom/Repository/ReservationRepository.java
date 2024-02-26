package com.example.carom.Repository;

import com.example.carom.Entity.Renter;
import com.example.carom.Entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRenter(Renter renter);
}