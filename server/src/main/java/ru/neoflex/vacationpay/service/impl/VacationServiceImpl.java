package ru.neoflex.vacationpay.service.impl;

import org.springframework.stereotype.Service;
import ru.neoflex.vacationpay.model.VacationPayResponse;
import ru.neoflex.vacationpay.service.VacationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Реализация интерфейса VacationService для расчета суммы оплаты отпуска.
 */
@Service
public class VacationServiceImpl implements VacationService {
    private static final double DAYS_AVG_MONTHLY = 29.3;

    /**
     * Метод для расчета суммы оплаты отпуска на основе годовой зарплаты и количества дней отпуска.
     *
     * @param yearSalary   годовая зарплата сотрудника
     * @param vacationDays количество дней отпуска
     * @return объект класса VacationPayResponse с суммой оплаты отпуска
     */
    @Override
    public VacationPayResponse getVacationPaySum(BigDecimal yearSalary, int vacationDays) {
        BigDecimal vacationPaySum = yearSalary.divide(BigDecimal.valueOf(DAYS_AVG_MONTHLY), RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(vacationDays)).setScale(2, RoundingMode.CEILING);
        return new VacationPayResponse(vacationPaySum);
    }
}
