package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.recurrency.RecurrencyType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "recurrency")
public record RecurrencyEntity(@Id ObjectId id, RecurrencyType type, LocalDate start, LocalDate end, Integer paid, Integer total) {}
