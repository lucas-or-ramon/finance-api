package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegistryResponse(String id, LocalDate date, Double value, String description, String creditCardId, RecurrencyResponse recurrency) {
}
