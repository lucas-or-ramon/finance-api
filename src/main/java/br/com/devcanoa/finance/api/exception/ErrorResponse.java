package br.com.devcanoa.finance.api.exception;

public record ErrorResponse(int status, String message, long timeStamp) {
}
