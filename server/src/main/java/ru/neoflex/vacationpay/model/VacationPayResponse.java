package ru.neoflex.vacationpay.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Класс для представления информации об оплате отпуска.
 */
@Data
public class VacationPayResponse {
    private final BigDecimal vacationPaySum; // Сумма оплаты отпуска.
}