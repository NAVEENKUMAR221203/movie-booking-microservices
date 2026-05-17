package com.example.SeatService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SeatService.entity.Seat;
import com.example.SeatService.service.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService service;

    /**
     * Public Endpoint: Anyone can browse the seating availability map
     */
    @GetMapping("/show/{showId}")
    public List<Seat> getSeatsByShow(@PathVariable Long showId) {
        return service.getSeatsByShow(showId);
    }

    /**
     * Admin Endpoint: Initialize theater grid rules for a new show
     */
    @PostMapping("/initialize/{showId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String initializeSeats(@PathVariable Long showId) {
        return service.initializeSeatsForShow(showId);
    }

    /**
     * User Endpoint: Lock an available seat
     */
    @PostMapping("/lock")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String lockSeat(
            @RequestParam Long showId,
            @RequestParam String seatNo) {

        return service.lockSeat(showId, seatNo);
    }

    /**
     * User Endpoint: Confirm finalized booking
     */
    @PostMapping("/confirm")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String confirmSeat(
            @RequestParam Long showId,
            @RequestParam String seatNo) {

        return service.confirmSeat(showId, seatNo);
    }
}