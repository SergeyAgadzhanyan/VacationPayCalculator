package ru.neoflex.vacationpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.neoflex.vacationpay.client.BaseClient;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Клиент для работы с API отпускных выплат.
 */
@Service
public class VacationClient extends BaseClient {
    private static final String API_PREFIX = "/calculate";

    /**
     * Конструктор класса VacationClient.
     *
     * @param serverUrl Адрес сервера, на котором расположен API отпускных выплат.
     * @param builder   RestTemplate-билдер.
     */
    @Autowired
    public VacationClient(@Value("${vocation-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    /**
     * Метод для получения информации о выплатах по отпуску.
     *
     * @param yearSalary    Годовой доход сотрудника.
     * @param vacationDays  Количество дней отпуска.
     * @return              Ответ с информацией о выплате отпуска.
     */
    public ResponseEntity<Object> getVacationPay(BigDecimal yearSalary, int vacationDays) {
        return get("?yearSalary={yearSalary}&vacationDays={vacationDays}",
                Map.of("yearSalary", yearSalary, "vacationDays", vacationDays));
    }

}
