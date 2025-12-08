package com.example.nto.service.impl;

import com.example.nto.dto.BookingCreationDto;
import com.example.nto.dto.BookingDetailsDto;
import com.example.nto.entity.Booking;
import com.example.nto.entity.Employee;
import com.example.nto.entity.Place;
import com.example.nto.exception.AlreadyBookedException;
import com.example.nto.exception.NoEmployeeFoundException;
import com.example.nto.exception.NoPlaceFoundException;
import com.example.nto.repository.BookingRepository;
import com.example.nto.repository.PlaceRepository;
import com.example.nto.service.BookingService;
import com.example.nto.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PlaceRepository placeRepository;


    // возвращает только за 4 дня букинги
    @Override
    public Map<String, List<BookingDetailsDto>> getAvailableBookings(String authCode) {
        if (!employeeService.isCodeValid(authCode)) {
            throw new NoEmployeeFoundException("Сотрудника с таким кодом не существует");
        }

        LocalDate beginDate = LocalDate.now();
        LocalDate endDate = beginDate.plusDays(3);

        List<Place> places = placeRepository.findAll();
        List<Booking> existingBookings = bookingRepository.findByDateBetween(beginDate, endDate);

        Map<LocalDate, Set<Long>> bookedPlaceIdsByDate = existingBookings.stream()
                .collect(Collectors.groupingBy(
                        Booking::getDate,
                        Collectors.mapping(booking -> booking.getPlace().getId(), Collectors.toSet())
                ));
        Map<String, List<BookingDetailsDto>> availableBookingsMap = new LinkedHashMap<>();

        for (int i = 0; i <= 3; i++) {
            LocalDate currentDate = beginDate.plusDays(i);
            Set<Long> bookedIds = bookedPlaceIdsByDate.getOrDefault(currentDate, Collections.emptySet());

            List<BookingDetailsDto> availablePlaces = places.stream()
                    .filter(place -> !bookedIds.contains(place.getId()))
                    .map(place -> new BookingDetailsDto(place.getId(), place.getPlace())) // getPlace <=> getName
                    .collect(Collectors.toList());

            availableBookingsMap.put(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), availablePlaces);
        }

        return availableBookingsMap;
    }

    @Override
    public void createBooking(String authCode, BookingCreationDto requestDto) {
        Employee employee = employeeService.getEmployeeByCode(authCode).orElseThrow(() -> new NoEmployeeFoundException("Сотрудника с таким кодом не существует"));
        Place place = placeRepository.findById(requestDto.getPlaceId()).orElseThrow(() -> new NoPlaceFoundException("Такого места не существует"));

        Optional<Booking> existingBooking = bookingRepository.findByDateAndPlaceId(requestDto.getDate(), requestDto.getPlaceId());
        if (existingBooking.isPresent()) {
            throw new AlreadyBookedException("Место уже забронировано");
        }

        Booking booking = Booking.builder()
                .date(requestDto.getDate())
                .employee(employee)
                .place(place)
                .build();

        bookingRepository.save(booking);
    }
}
