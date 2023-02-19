package br.com.devcanoa.finance.api.controller.response;

import java.time.LocalDate;

public record CreditCardResponse(String id, String name, LocalDate dueDate, Double total) {
}
