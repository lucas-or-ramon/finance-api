package br.com.devcanoa.finance.api.infra.adapter.entity;

import br.com.devcanoa.finance.api.domain.recurrency.RecurrencyType;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public record Recurrency(ObjectId id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {}
