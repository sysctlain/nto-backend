package com.example.nto.service;

import com.example.nto.dto.BookingCreationDto;
import com.example.nto.dto.BookingDetailsDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingService {
    Optional<Map<String, List<BookingDetailsDto>>> getAvailableBookings(String authCode);

    BookingStatus createBooking(String authCode, BookingCreationDto requestDto);
}
