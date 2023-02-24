package br.com.devcanoa.finance.api.app.adapter.handler;

import br.com.devcanoa.finance.api.domain.exception.ErrorResponse;
import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import br.com.devcanoa.finance.api.domain.exception.RegistryAlreadyExistsException;
import br.com.devcanoa.finance.api.domain.exception.RegistryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FinanceExceptionHandler {

    Logger logger = LoggerFactory.getLogger(FinanceExceptionHandler.class);

    @ExceptionHandler(value = RegistryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final RegistryNotFoundException exception) {
        return getErrorResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RegistryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(final RegistryAlreadyExistsException exception) {
        return getErrorResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FinanceException.class)
    public ResponseEntity<ErrorResponse> handleException(final FinanceException exception) {
        return getErrorResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        return getErrorResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseEntity(final Exception exception, final HttpStatus httpStatus) {
        logger.error(exception.getMessage());
        final var errorResponse = new ErrorResponse(httpStatus.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
