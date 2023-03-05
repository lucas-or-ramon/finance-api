package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class RegistryEntity {

    @Id
    private final ObjectId id;
    private final Double value;
    private final LocalDate date;
    private final String description;
    private final ObjectId creditCardId;
    private final RecurrencyEntity recurrencyEntity;

    public RegistryEntity(final ObjectId id, final LocalDate date, final Double value, final String description, final ObjectId creditCardId, final RecurrencyEntity recurrencyEntity) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
        this.creditCardId = creditCardId;
        this.recurrencyEntity = recurrencyEntity;
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

    public RecurrencyEntity getRecurrency() {
        return recurrencyEntity;
    }
}
