package br.com.devcanoa.finance.api.adapter.inbound.handler;

import br.com.devcanoa.finance.api.domain.exception.ErrorResponse;
import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FinanceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinanceExceptionHandler.class);

    @ExceptionHandler(value = FinanceException.class)
    public ResponseEntity<ErrorResponse> handleException(final FinanceException exception) {
        return getErrorResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        return getErrorResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseEntity(final Exception exception, final HttpStatus httpStatus) {
        LOGGER.error(exception.getMessage());
        final var errorResponse = new ErrorResponse(httpStatus.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
