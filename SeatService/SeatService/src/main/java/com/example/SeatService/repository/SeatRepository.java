package com.example.SeatService.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.example.SeatService.entity.Seat;
import com.example.SeatService.enums.SeatStatus;

import jakarta.persistence.LockModeType;

@Repository
public interface SeatRepository
        extends JpaRepository<Seat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Seat> findByShowIdAndSeatNumber(
            Long showId,
            String seatNumber
    );

    List<Seat> findByStatusAndLockedAtBefore(
            SeatStatus status,
            LocalDateTime time
    );

    // 🔥 FIXED: Added the missing lookup method to fetch all seats for a given show layout
    List<Seat> findByShowId(Long showId);
}