package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.recurrency.RecurrencyType;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public record RecurrencyEntity(ObjectId id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {}
