package com.example.nto.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * TODO: ДОРАБОТАТЬ в рамках задания
 * =================================
 * МОЖНО: Добавлять методы, аннотации, зависимости
 * НЕЛЬЗЯ: Изменять название класса и пакета
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private long id;

    private LocalDate date;

    @ManyToOne(targetEntity = Place.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private Employee employee;
}
