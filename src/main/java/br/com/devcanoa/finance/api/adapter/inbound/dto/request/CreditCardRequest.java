package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import java.time.LocalDate;

public record CreditCardRequest(String name, LocalDate dueDate) {
}
