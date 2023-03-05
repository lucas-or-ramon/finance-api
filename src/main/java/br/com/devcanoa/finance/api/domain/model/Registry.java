package br.com.devcanoa.finance.api.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Registry {

    private final String id;
    private final Double value;
    private final LocalDate date;
    private final String description;
    private final String creditCardId;
    private final Recurrency recurrencyEntity;

    public Registry(final String id, final LocalDate date, final Double value, final String description, final String creditCardId, final Recurrency recurrency) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
        this.creditCardId = creditCardId;
        this.recurrencyEntity = recurrency;
    }

    public String getId() {
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

    public String getCreditCardId() {
        return creditCardId;
    }

    public Recurrency getRecurrency() {
        return recurrencyEntity;
    }
}
