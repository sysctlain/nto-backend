package com.example.nto.controller;
import com.example.nto.dto.BookingCreationDto;
import com.example.nto.dto.BookingDetailsDto;
import com.example.nto.dto.EmployeeInfoDto;
import com.example.nto.exception.EmptyCodeException;
import com.example.nto.exception.NoSuchCodeException;
import com.example.nto.service.BookingService;
import com.example.nto.service.BookingStatus;
import com.example.nto.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("{code}/booking")
    public ResponseEntity<?> booking(@PathVariable String code) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Empty code");
        }

        Optional<Map<String, List<BookingDetailsDto>>> availableBookings = bookingService.getAvailableBookings(code);

        if (availableBookings.isPresent()) {
            return new ResponseEntity<>(availableBookings.get(), HttpStatus.OK);
        } else  {
            throw new NoSuchCodeException("No such code");
        }
    }

    @PostMapping("{code}/book")
    public ResponseEntity<?> book(@PathVariable String code,  @Valid @RequestBody BookingCreationDto requestDto) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Empty code");
        }

        BookingStatus result = bookingService.createBooking(code, requestDto);
        return new ResponseEntity<>("бронирование успешно создано", HttpStatus.CREATED);
        // todo BAD_REQUEST
    }
}
