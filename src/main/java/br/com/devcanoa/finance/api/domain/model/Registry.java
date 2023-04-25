package br.com.devcanoa.finance.api.domain.model;

import br.com.devcanoa.finance.api.domain.exception.RecurrenceException;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public class Registry {

    private final String id;
    private final Double value;
    private final String category;
    private final String description;
    private final String creditCardId;
    private final Recurrence recurrence;

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
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

    public String getCreditCardId() {
        return creditCardId;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public boolean hasCreditCard() {
        return creditCardId != null;
    }

    public static final class Builder {
        private Registry registry;
        private String id;
        private Double value;
        private String category;
        private String description;
        private String creditCardId;
        private Recurrence recurrence;

        private Builder() {}

        private Builder(final Registry registry) {
            this.registry = registry;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder value(final Double value) {
            this.value = value;
            return this;
        }

        public Builder category(final String category) {
            this.category = category;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder creditCardId(final String creditCardId) {
            this.creditCardId = creditCardId;
            return this;
        }

        public Builder recurrence(final Recurrence recurrence) {
            this.recurrence = recurrence;
            return this;
        }

        public Registry build() {
            if (registry != null) {
                return Registry.builder()
                        .id(id)
                        .value(value)
                        .category(category)
                        .recurrence(recurrence)
                        .description(description)
                        .creditCardId(getValue(registry.getCreditCardId(), creditCardId))
                        .build();
            }
            if (id == null || value == null || category == null || description == null || recurrence == null) {
                throw new RegistryException("Registry must have id, value, category, description and recurrence");
            }

            if (!recurrence.isValid()) {
                throw new RecurrenceException("Recurrence must be valid");
            }

            return new Registry(id, value, description, category, creditCardId, recurrence);
        }

        private <T> T getValue(final T registryValue, final T builderValue) {
            return builderValue != null ? builderValue : registryValue;
        }
    }

    private Registry(final String id, final Double value, final String description, final String category,
                    final String creditCardId, final Recurrence recurrence) {
        this.id = id;
        this.value = value;
        this.category = category;
        this.recurrence = recurrence;
        this.description = description;
        this.creditCardId = creditCardId;
    }
}
