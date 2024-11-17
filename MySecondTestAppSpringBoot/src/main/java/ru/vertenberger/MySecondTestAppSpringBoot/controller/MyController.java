package ru.vertenberger.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vertenberger.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.vertenberger.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.vertenberger.MySecondTestAppSpringBoot.model.*;
import ru.vertenberger.MySecondTestAppSpringBoot.service.*;
import ru.vertenberger.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;

    private final ModifyResponseService modifyResponseService;

    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifyFieldsRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = collectInitialResponse(request);

        try {
            request.checkUid();
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            return buildErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST, e);
        } catch (ValidationFailedException e) {
            return buildErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST, e);
        } catch (Exception e) {
            return buildErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        applyModifications(request, response);
        log.info("Ответ: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<Response> buildErrorResponse(Response response, ErrorCodes errorCode, ErrorMessages errorMessage, HttpStatus status, Exception e) {
        log.error("Error occurred: {}", e.getMessage(), e);
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return new ResponseEntity<>(response, status);
    }

    private Response collectInitialResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void applyModifications(Request request, Response response) {
        modifyResponseService.modify(response);
        modifyRequestService.modify(request);
    }
}

