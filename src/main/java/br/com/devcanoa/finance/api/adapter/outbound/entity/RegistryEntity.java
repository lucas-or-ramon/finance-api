package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.Registry;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class RegistryEntity {

    @Id
    private final ObjectId id;
    private final Double value;
    private final String category;
    private final String description;
    private final ObjectId creditCardId;
    private final RecurrenceEntity recurrence;

    public RegistryEntity(final ObjectId id,
                          final Double value,
                          final String category,
                          final String description,
                          final ObjectId creditCardId,
                          final RecurrenceEntity recurrence) {
        this.id = id;
        this.value = value;
        this.category = category;
        this.recurrence = recurrence;
        this.description = description;
        this.creditCardId = creditCardId;
    }

    public Registry mapToDomain() {
        return Registry.builder()
                .id(id.toString())
                .value(value)
                .category(category)
                .creditCardId(creditCardId != null ? creditCardId.toString() : null)
                .recurrence(recurrence.mapToDomain())
                .description(description)
                .build();
    }

    public boolean hasCreditCard() {
        return creditCardId != null;
    }

    public ObjectId getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public ObjectId getCreditCardId() {
        return creditCardId;
    }

    public RecurrenceEntity getRecurrence() {
        return recurrence;
    }
}
