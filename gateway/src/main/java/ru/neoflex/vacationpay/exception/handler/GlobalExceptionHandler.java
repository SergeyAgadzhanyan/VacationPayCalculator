package ru.neoflex.vacationpay.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.neoflex.vacationpay.exception.ErrorResponse;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

/**
 * Глобальный обработчик исключений для контроллеров.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Обработчик исключений для невалидных аргументов метода.
     *
     * @param ex Исключение MethodArgumentNotValidException или BindException.
     * @return   Ответ с сообщением об ошибке.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class,
            MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(RuntimeException ex) {
        log.warn("Получен статус 400 Bad request {}", ex.getMessage(), ex);
        return new ErrorResponse(ex.getMessage(), ex.getMessage());
    }

    /**
     * Обработчик исключений для всех остальных исключений.
     *
     * @param ex Исключение любого типа Throwable.
     * @return   Ответ с сообщением об ошибке.
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMethodArgumentNotValidException(Throwable ex) {
        log.error("Получен статус 500 Internal server error {}", ex.getMessage(), ex);
        return new ErrorResponse(ex.toString(), ex.getMessage());
    }
}
