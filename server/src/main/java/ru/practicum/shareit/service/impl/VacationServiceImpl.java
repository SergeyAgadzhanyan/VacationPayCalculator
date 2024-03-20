package ru.practicum.shareit.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.VacationServer;
import ru.practicum.shareit.model.VacationPayResponse;
import ru.practicum.shareit.service.VacationService;

import java.math.BigDecimal;

@Service
public class VacationServiceImpl  implements VacationService {
    private static final double DAYS_AVG_MONTHLY = 29.3;

    @Override
    public VacationPayResponse getVacationPaySum(BigDecimal yearSalary, int vacationDays) {
        //todo
        return null;
    }
}
