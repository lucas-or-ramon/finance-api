package br.com.devcanoa.finance.api.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record CreditCard(String id, String name, LocalDate dueDate) {}
