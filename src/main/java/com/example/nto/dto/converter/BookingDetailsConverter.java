package com.example.nto.dto.converter;

import com.example.nto.dto.BookingDetailsDto;
import com.example.nto.entity.Booking;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookingDetailsConverter {
    public BookingDetailsDto toDto(Booking booking) {
        return BookingDetailsDto.builder()
                .place(booking.getPlace().getPlace()) // awkward
                .build();
    }
}
