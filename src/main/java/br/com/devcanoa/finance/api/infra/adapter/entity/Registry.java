package br.com.devcanoa.finance.api.infra.adapter.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Registry {

    @Id
    private final ObjectId id;
    private final Double value;
    private final LocalDate date;
    private final String description;
    private final ObjectId creditCardId;
    private final Recurrency recurrency;

    public Registry(final ObjectId id, final LocalDate date, final Double value, final String description, final ObjectId creditCardId, final Recurrency recurrency) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
        this.creditCardId = creditCardId;
        this.recurrency = recurrency;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public ObjectId getCreditCardId() {
        return creditCardId;
    }

    public Recurrency getRecurrency() {
        return recurrency;
    }
}