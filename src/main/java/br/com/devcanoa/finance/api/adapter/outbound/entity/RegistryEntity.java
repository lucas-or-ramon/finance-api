package br.com.devcanoa.finance.api.adapter.outbound.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class RegistryEntity {

    @Id
    private final ObjectId id;
    private final Double value;
    private final String description;
    private final ObjectId creditCardId;
    private final RecurrenceEntity recurrence;

    public RegistryEntity(final ObjectId id, final Double value, final String description, final ObjectId creditCardId, final RecurrenceEntity recurrence) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.creditCardId = creditCardId;
        this.recurrence = recurrence;
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

    public ObjectId getCreditCardId() {
        return creditCardId;
    }

    public RecurrenceEntity getRecurrence() {
        return recurrence;
    }
}
