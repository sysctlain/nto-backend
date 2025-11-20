package com.example.nto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String place;
}
