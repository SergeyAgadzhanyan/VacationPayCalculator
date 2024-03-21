package ru.neoflex.vacationpay.service;

import ru.neoflex.vacationpay.model.VacationPayResponse;

import java.math.BigDecimal;

/**
 * Интерфейс, предоставляющий метод для расчета суммы оплаты отпуска.
 */
public interface VacationService {

    /**
     * Метод для расчета суммы оплаты отпуска на основе годовой зарплаты и количества дней отпуска.
     *
     * @param yearSalary   годовая зарплата сотрудника
     * @param vacationDays количество дней отпуска
     * @return объект класса VacationPayResponse с суммой оплаты отпуска
     */
    VacationPayResponse getVacationPaySum(BigDecimal yearSalary, int vacationDays);
}
