package br.com.devcanoa.finance.api.domain.model;

import java.time.LocalDate;

public record CreditCard(String id, String name, LocalDate dueDate) {}
