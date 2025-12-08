package com.example.nto.dto.converter;

import com.example.nto.dto.BookingDetailsDto;
import com.example.nto.dto.EmployeeInfoDto;
import com.example.nto.entity.Booking;
import com.example.nto.entity.Employee;
import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class EmployeeInfoConverter {
    public EmployeeInfoDto toDto(Employee employee) {
        Map<String, BookingDetailsDto> bookingMap = new HashMap<>();
        if (employee.getBookingList() != null) {
            for (Booking booking : employee.getBookingList()) {
                String dateKey = booking.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String placeName = booking.getPlace().getPlace();

                bookingMap.put(dateKey, new BookingDetailsDto(
                        booking.getId(),
                        placeName
                ));
            }
        }

        return EmployeeInfoDto.builder().
                name(employee.getName()).
                booking(bookingMap).
                photoUrl(employee.getPhotoUrl()).
                build();
    }
}
