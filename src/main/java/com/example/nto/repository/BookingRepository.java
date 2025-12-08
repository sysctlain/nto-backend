package com.example.nto.repository;

import com.example.nto.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByDateAndPlaceId(LocalDate date, long placeId);

    List<Booking> findByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
    // maybe add function to add
}
