package ru.neoflex.vacationpay.client;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Базовый класс для клиентов API.
 */
public class BaseClient {
    /**
     * RestTemplate для выполнения HTTP запросов.
     */
    protected final RestTemplate rest;

    /**
     * Конструктор для инициализации RestTemplate.
     *
     * @param rest RestTemplate
     */
    public BaseClient(RestTemplate rest) {
        this.rest = rest;
    }

    /**
     * Подготавливает ответ от API.
     *
     * @param response Ответ от API
     * @return Подготовленный ответ
     */
    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());

        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }

    /**
     * Выполняет GET запрос.
     *
     * @param path Путь запроса
     * @return Ответ от API
     */
    protected ResponseEntity<Object> get(String path) {
        return get(path, null);
    }

    /**
     * Выполняет GET запрос с параметрами.
     *
     * @param path       Путь запроса
     * @param parameters Параметры запроса
     * @return Ответ от API
     */
    protected ResponseEntity<Object> get(String path, @Nullable Map<String, Object> parameters) {
        return makeAndSendRequest(HttpMethod.GET, path, parameters, null);
    }

    /**
     * Выполняет POST запрос.
     *
     * @param path Путь запроса
     * @param body Тело запроса
     * @param <T>  Тип тела запроса
     * @return Ответ от API
     */
    protected <T> ResponseEntity<Object> post(String path, T body) {
        return post(path, null, body);
    }

    /**
     * Выполняет POST запрос с параметрами.
     *
     * @param path       Путь запроса
     * @param parameters Параметры запроса
     * @param body       Тело запроса
     * @param <T>        Тип тела запроса
     * @return Ответ от API
     */
    protected <T> ResponseEntity<Object> post(String path, @Nullable Map<String, Object> parameters, T body) {
        return makeAndSendRequest(HttpMethod.POST, path, parameters, body);
    }

    /**
     * Создает и отправляет HTTP запрос.
     *
     * @param method     HTTP метод
     * @param path       Путь запроса
     * @param parameters Параметры запроса
     * @param body       Тело запроса
     * @param <T>        Тип тела запроса
     * @return Ответ от API
     */
    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path, @Nullable Map<String, Object> parameters, @Nullable T body) {
        HttpEntity<T> requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> serverResponse;
        try {
            if (parameters != null) {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class, parameters);
            } else {
                serverResponse = rest.exchange(path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(serverResponse);
    }

    /**
     * Создает и возвращает HTTP заголовки по умолчанию.
     *
     * @return HTTP заголовки по умолчанию
     */
    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headers;
    }
}
