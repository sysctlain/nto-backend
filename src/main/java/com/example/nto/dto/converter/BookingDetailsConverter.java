package com.example.nto.dto.converter;

import com.example.nto.dto.BookingDetailsDto;
import com.example.nto.entity.Place;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookingDetailsConverter {
    public BookingDetailsDto toDto(Place place) {
        return BookingDetailsDto.builder()
                .id(place.getId())
                .place(place.getPlace())
                .build();
    }
}
