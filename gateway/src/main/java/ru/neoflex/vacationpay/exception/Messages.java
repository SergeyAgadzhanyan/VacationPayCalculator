package ru.neoflex.vacationpay.exception;

import org.springframework.util.StringUtils;

/**
 * Перечисление для сообщений об ошибке.
 */
public enum Messages {
    VALIDATION_EXCEPTION,
    VALUE_MUST_BE_GREATER_THAN_OR_EQUAL_TO_1,
    INVALID_ARGUMENTS;

    /**
     * Получение отформатированного сообщения.
     *
     * @return Отформатированное сообщение
     */
    public String getMessage() {
        return StringUtils.capitalize(this.name()
                .toLowerCase().replaceAll("_", " "));
    }
}