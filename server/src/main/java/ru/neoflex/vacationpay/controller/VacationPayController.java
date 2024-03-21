package ru.neoflex.vacationpay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.vacationpay.model.VacationPayResponse;
import ru.neoflex.vacationpay.service.VacationService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/calculate")
public class VacationPayController {
    private final VacationService vacationService;


    @GetMapping()
    public VacationPayResponse getVacationPay(@RequestParam BigDecimal yearSalary, @RequestParam int vacationDays) {
        return vacationService.getVacationPaySum(yearSalary, vacationDays);
    }


}
