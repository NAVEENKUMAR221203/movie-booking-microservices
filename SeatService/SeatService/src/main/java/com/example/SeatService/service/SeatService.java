package com.example.SeatService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SeatService.entity.Seat;
import com.example.SeatService.enums.SeatStatus;
import com.example.SeatService.repository.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository repo;

    
    public List<Seat> getSeatsByShow(Long showId) {
        return repo.findByShowId(showId);
    }

 
    @Transactional
    public String initializeSeatsForShow(Long showId) {
        
        if (!repo.findByShowId(showId).isEmpty()) {
            return "Seats already initialized for Show ID " + showId;
        }

        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        List<Seat> theaterSeats = new ArrayList<>();

        for (char row : rows) {
            for (int i = 1; i <= 10; i++) {
                Seat seat = new Seat();
                seat.setShowId(showId);
                seat.setSeatNumber(row + String.valueOf(i));
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setLockedAt(null);
                theaterSeats.add(seat);
            }
        }

        repo.saveAll(theaterSeats);
        return "Successfully initialized 100 seats for Show ID " + showId;
    }

    
    @Transactional
    public String lockSeat(Long showId, String seatNo) {
        Optional<Seat> seatOptional = repo.findByShowIdAndSeatNumber(showId, seatNo);

        if (seatOptional.isEmpty()) {
            return "Error: Seat " + seatNo + " does not exist for Show ID " + showId;
        }

        Seat seat = seatOptional.get();

        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            return "Seat Not Available";
        }

        seat.setStatus(SeatStatus.LOCKED);
        seat.setLockedAt(LocalDateTime.now());

        repo.save(seat);
        return "Seat Locked";
    }

   
    @Transactional
    public String confirmSeat(Long showId, String seatNo) {
        Optional<Seat> seatOptional = repo.findByShowIdAndSeatNumber(showId, seatNo);

        if (seatOptional.isEmpty()) {
            return "Error: Seat " + seatNo + " does not exist for Show ID " + showId;
        }

        Seat seat = seatOptional.get();
        
        if (seat.getStatus() == SeatStatus.BOOKED) {
            return "Seat Already Booked";
        }

        seat.setStatus(SeatStatus.BOOKED);
        seat.setLockedAt(null); 

        repo.save(seat);
        return "Seat Booked";
    }
}