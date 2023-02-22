package br.com.devcanoa.finance.api.controller.response;

import java.time.LocalDate;
import java.util.List;

public record CreditCardResponse(String id, String name, LocalDate dueDate, Double total, List<RegistryResponse> registries) {
}
