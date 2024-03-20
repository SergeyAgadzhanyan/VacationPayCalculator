package ru.practicum.shareit.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VacationPayResponse {
    private final BigDecimal vacationPaySum;
}
