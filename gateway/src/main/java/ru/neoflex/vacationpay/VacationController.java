package ru.neoflex.vacationpay;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Контроллер для валидации данных связанных с отпускными выплатами.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/calculate")
@Validated
public class VacationController {
    private final VacationClient vacationClient;

    /**
     * Метод для получения информации о выплатах по отпуску.
     *
     * @param yearSalary    Годовой доход сотрудника. Минимальное значение 1.
     * @param vacationDays  Количество дней отпуска. Минимальное значение 1.
     * @return              Ответ с информацией о выплате отпуска.
     */
    @GetMapping
    public ResponseEntity<Object> getVacationPay(@RequestParam @Min(value = 1, message = "Значение должно быть больше или равно 1") BigDecimal yearSalary,
                                                 @RequestParam @Min(value = 1, message = "Значение должно быть больше или равно 1") int vacationDays) {
        return vacationClient.getVacationPay(yearSalary, vacationDays);
    }

}
