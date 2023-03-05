package br.com.devcanoa.finance.api.domain.model;

import br.com.devcanoa.finance.api.domain.model.recurrency.RecurrencyType;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public record Recurrency(String id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {}
