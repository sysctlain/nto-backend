package com.example.nto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDetailsDto {
    private Long id;
    private String place;
}
