package com.example.SeatService.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.SeatService.entity.Seat;
import com.example.SeatService.enums.SeatStatus;
import com.example.SeatService.repository.SeatRepository;

@Component
public class SeatReleaseScheduler {

    @Autowired
    private SeatRepository repo;

    @Scheduled(fixedRate = 30000)
    public void releaseSeats() {

        LocalDateTime expiry =
                LocalDateTime.now().minusMinutes(2);

        List<Seat> seats =
                repo.findByStatusAndLockedAtBefore(
                        SeatStatus.LOCKED,
                        expiry
                );

        for(Seat seat : seats) {

            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setLockedAt(null);
        }

        repo.saveAll(seats);
    }
}
