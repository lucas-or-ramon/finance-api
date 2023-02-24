package br.com.devcanoa.finance.api.domain.dto.request;

import java.time.LocalDate;

public record CreditCardRequest(String name, LocalDate dueDate) {
}
