package com.example.BookingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookingService.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String createBooking(
            @RequestParam Long userId,
            @RequestParam Long showId,
            @RequestParam String seatNo) {

        return service.createBooking(
                userId,
                showId,
                seatNo
        );
    }

    @PostMapping("/confirm/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String confirmBooking(
            @PathVariable Long id) {

        return service.confirmBooking(id);
    }
}
