package ru.vertenberger.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vertenberger.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * Уникальный идентификатор сообщения.
     * Обязательный. Максимум 32 символа.
     */
    @NotBlank
    @Size(max=32)
    private String uid;

    /**
     * Уникальный идентификатор операции.
     * Обязательный. Максимум 32 символа.
     */
    @NotBlank
    @Size(max=32)
    private String operationUid;

    /**
     * Имя системы отправителя.
     * Необязательный параметр.
     */
    private Systems systemName;

    /**
     * Системное время создания сообщения.
     * Обязательный параметр.
     */
    @NotBlank
    private String systemTime;

    /**
     * Наименование ресурса.
     * Необязательный параметр.
     */
    private String source;

    /**
     * Наименование позиции сотрудника.
     * Необязательный параметр.
     */
    private Positions position;

    /**
     * Размер зарплаты сотрудника.
     * Необязательный параметр.
     */
    private Double salary;

    /**
     * Размер бонуса (премии).
     * Необязательный параметр.
     */
    private Double bonus;

    /**
     * Количество рабочих дней.
     * Необязательный параметр.
     */
    private Integer workDays;

    /**
     * Уникальный идентификатор коммуникации.
     * Обязательный. Минимум 1, максимум 100000.
     */
    @Min(1)
    @Max(100000)
    private Integer communicationId;

    /**
     * Уникальный идентификатор шаблона.
     * Необязательный параметр.
     */
    private Integer templateId;

    /**
     * Код продукта.
     * Необязательный параметр.
     */
    private Integer productCode;

    /**
     * Смс-код.
     * Необязательный параметр.
     */
    private Integer smsCode;

    public void checkUid() throws UnsupportedCodeException {
        if ("123".equals(uid)) {
            throw new UnsupportedCodeException("Unsupported uid: " + uid);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                "operationUid='" + operationUid + '\'' +
                "systemName='" + systemName + '\'' +
                "systemTime='" + systemTime + '\'' +
                "source'" + source + '\'' +
                "position'" + position + '\'' +
                "salary'" + salary + '\'' +
                "bonus'" + bonus + '\'' +
                "workDays'" + workDays + '\'' +
                "communicationId=" + communicationId +
                "templateId=" + templateId +
                "productCode=" + productCode +
                "smsCode" + smsCode +
                "}";
    }
}

