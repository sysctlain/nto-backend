package com.example.nto.controller;

import com.example.nto.dto.BookingCreationDto;
import com.example.nto.exception.EmptyCodeException;
import com.example.nto.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("{code}/booking")
    public ResponseEntity<?> booking(@PathVariable String code) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Был передан пустой код сотрудника");
        }

        return ResponseEntity.ok(bookingService.getAvailableBookings(code));
    }

    @PostMapping("{code}/book")
    public ResponseEntity<?> book(@PathVariable String code,  @Valid @RequestBody BookingCreationDto requestDto) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Был передан пустой код сотрудника");
        }

        bookingService.createBooking(code, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Бронирование успешно создано");
    }
}
