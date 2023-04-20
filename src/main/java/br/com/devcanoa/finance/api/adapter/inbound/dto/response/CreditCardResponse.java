package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreditCardResponse(String id, String name, int dueDate) {
}
