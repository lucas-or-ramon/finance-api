package br.com.devcanoa.finance.api.domain.response;

import br.com.devcanoa.finance.api.domain.recurrency.RecurrencyType;

import java.time.LocalDate;

public record RecurrencyResponse(String id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {
}
