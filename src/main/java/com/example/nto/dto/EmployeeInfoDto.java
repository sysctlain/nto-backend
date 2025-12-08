package com.example.nto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoDto {
    private String name;
    private Map<String, BookingDetailsDto> booking;
    private String photoUrl;
}

