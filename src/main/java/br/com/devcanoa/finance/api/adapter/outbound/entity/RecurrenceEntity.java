package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;

public record RecurrenceEntity(RecurrenceType type, int dueDate, FinanceDate start, FinanceDate end) {}
