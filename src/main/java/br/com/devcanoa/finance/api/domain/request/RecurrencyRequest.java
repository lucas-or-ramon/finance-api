package br.com.devcanoa.finance.api.domain.request;

import br.com.devcanoa.finance.api.domain.recurrency.RecurrencyType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record RecurrencyRequest(@NotNull RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {}
