package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credit-card")
public record CreditCardEntity(@Id ObjectId id, String name, int dueDate) {
}
