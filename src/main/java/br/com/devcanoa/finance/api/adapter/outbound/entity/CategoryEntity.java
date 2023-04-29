package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
public record CategoryEntity(@Id ObjectId id, String name) {}
