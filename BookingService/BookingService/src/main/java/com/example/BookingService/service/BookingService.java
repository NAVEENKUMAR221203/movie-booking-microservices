package com.example.BookingService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.BookingService.entity.Booking;
import com.example.BookingService.enums.BookingStatus;
import com.example.BookingService.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    public String createBooking(
            Long userId,
            Long showId,
            String seatNo) {

        String response =
                restTemplate.postForObject(
                        "http://SEAT-SERVICE/seats/lock?showId="
                                + showId
                                + "&seatNo="
                                + seatNo,
                        null,
                        String.class
                );

        if(!response.equals("Seat Locked")) {
            return response;
        }

        Booking booking = new Booking();

        booking.setUserId(userId);
        booking.setShowId(showId);
        booking.setSeatNo(seatNo);
        booking.setStatus(BookingStatus.PENDING);

        repo.save(booking);

        return "Booking Created";
    }

    public String confirmBooking(Long id) {

        Booking booking =
                repo.findById(id).orElseThrow();

        restTemplate.postForObject(
                "http://SEAT-SERVICE/seats/confirm?showId="
                        + booking.getShowId()
                        + "&seatNo="
                        + booking.getSeatNo(),
                null,
                String.class
        );

        booking.setStatus(BookingStatus.CONFIRMED);

        repo.save(booking);

        return "Booking Confirmed";
    }
}