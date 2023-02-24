package br.com.devcanoa.finance.api.infra.adapter.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "credit-card")
public record CreditCard(@Id ObjectId id, String name, LocalDate dueDate) {
}
