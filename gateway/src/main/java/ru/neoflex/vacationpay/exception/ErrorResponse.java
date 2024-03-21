package ru.neoflex.vacationpay.exception;

import lombok.Data;

/**
 * Класс для представления сообщения об ошибке.
 */
@Data
public class ErrorResponse {
    /**
     * Строка с описанием ошибки.
     */
    private final String error;

    /**
     * Строка с подробным описанием ошибки.
     */
    private final String description;
}
