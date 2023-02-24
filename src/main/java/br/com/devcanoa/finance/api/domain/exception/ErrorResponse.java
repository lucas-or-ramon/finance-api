package br.com.devcanoa.finance.api.domain.exception;

public record ErrorResponse(int status, String message, long timeStamp) {
}
