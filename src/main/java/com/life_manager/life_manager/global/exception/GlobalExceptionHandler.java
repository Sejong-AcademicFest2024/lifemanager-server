package com.life_manager.life_manager.global.exception;

import com.life_manager.life_manager.global.error.ErrorCode;
import com.life_manager.life_manager.global.response.ErrorResponse;
import com.life_manager.life_manager.global.response.GlobalResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.FailedMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage());
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage(), e);
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorMessage);
        GlobalResponse response = GlobalResponse.fail(status.value(), errorResponse);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalResponse> handleConstraintViolationException(
            ConstraintViolationException e) {
        log.error("ConstraintViolationException : {}", e.getMessage(), e);

        Map<String, Object> bindingErrors = new HashMap<>();
        e.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), bindingErrors.toString());
        final GlobalResponse response =
                GlobalResponse.fail(HttpStatus.BAD_REQUEST.value(), errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<GlobalResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
        final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response = GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response = GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage(), e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse errorResponse = ErrorResponse.of(errorCode.name(), errorCode.getMessage());
        final GlobalResponse response = GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    // sms 예외 처리
    @ExceptionHandler(NurigoMessageNotReceivedException.class)
    public ResponseEntity<GlobalResponse> handleNurigoMessageNotReceivedException(NurigoMessageNotReceivedException e) {
        log.error("NurigoMessageNotReceivedException : {}", e.getMessage(), e);
        FailedMessage failedMessage = e.getFailedMessageList().get(0);

        final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), failedMessage.getStatusMessage());
        final GlobalResponse response = GlobalResponse.fail(HttpStatus.SERVICE_UNAVAILABLE.value(), errorResponse);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler({NurigoUnknownException.class, NurigoEmptyResponseException.class})
    public ResponseEntity<GlobalResponse> handleOtherNurigoMessageException(Exception e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());

        final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), e.getMessage());
        final GlobalResponse response = GlobalResponse.fail(HttpStatus.SERVICE_UNAVAILABLE.value(), errorResponse);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GlobalResponse> handleException(Exception e) {
        log.error("Internal Server Error : {}", e.getMessage(), e);
        final ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.of(e.getClass().getSimpleName(), internalServerError.getMessage());
        final GlobalResponse response = GlobalResponse.fail(internalServerError.getStatus().value(), errorResponse);

        return ResponseEntity.status(internalServerError.getStatus()).body(response);
    }
}
