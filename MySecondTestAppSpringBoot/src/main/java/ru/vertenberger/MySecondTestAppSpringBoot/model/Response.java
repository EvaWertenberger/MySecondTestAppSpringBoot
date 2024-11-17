package ru.vertenberger.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    /**
     * Уникальный идентификатор сообщения.
     * Обязательный параметр.
     */
    private String uid;

    /**
     * Уникальный идентификатор операции.
     * Обязательный параметр.
     */
    private String operationUid;

    /**
     * Имя системы отправителя.
     * Обязательный параметр, всегда "ERP".
     */
    private String systemTime;

    /**
     * Код статуса обработки.
     * Возможные значения: "success", "failed".
     */
    private Codes code;

    /**
     * Размер премии сотруднику.
     */
    private Double annualBonus;

    /**
     * Код ошибки, если обработка завершилась неуспешно.
     * Возможные значения: "UnsupportedCodeException", "ValidationException", "UnknownException".
     */
    private ErrorCodes errorCode;

    /**
     * Сообщение об ошибке, если обработка завершилась неуспешно.
     * Возможные значения: "Не поддерживаемая ошибка", "Ошибка валидации", "Произошла непредвиденная ошибка".
     */
    private ErrorMessages errorMessage;
}