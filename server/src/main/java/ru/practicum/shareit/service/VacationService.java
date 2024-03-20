package ru.practicum.shareit.service;

import ru.practicum.shareit.model.VacationPayResponse;

import java.math.BigDecimal;

public interface VacationService {
    VacationPayResponse getVacationPaySum(BigDecimal yearSalary, int vacationDays);
}
