package ru.practicum.shareit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.model.VacationPayResponse;
import ru.practicum.shareit.service.VacationService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/calculate")
public class VacationPayController {
    private final VacationService vacationService;


    @GetMapping()
    public VacationPayResponse getUserById(@RequestParam BigDecimal yearSalary, @RequestParam int vacationDays) {
        return vacationService.getVacationPaySum(yearSalary, vacationDays);
    }


}
