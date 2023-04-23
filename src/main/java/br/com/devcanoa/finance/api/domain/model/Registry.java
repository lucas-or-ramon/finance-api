package br.com.devcanoa.finance.api.domain.model;

import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public class Registry {

    private final String id;
    private final Double value;
    private final String category;
    private final String description;
    private final CreditCard creditCard;
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public static final class Builder {
        private String id;
        private Double value;
        private String category;
        private String description;
        private CreditCard creditCard;
        private Recurrence recurrence;

        private Builder() {}

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

        public Builder creditCard(final CreditCard creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public Builder recurrence(final Recurrence recurrence) {
            this.recurrence = recurrence;
            return this;
        }

        public Registry build() {
            if (id == null || value == null || category == null || description == null || recurrence == null) {
                throw new IllegalArgumentException("Registry must have id, value, category, description and recurrence");
            }
            return new Registry(id, value, description, category, creditCard, recurrence);
        }
    }

    private Registry(final String id, final Double value, final String description, final String category,
                    final CreditCard creditCard, final Recurrence recurrence) {
        this.id = id;
        this.value = value;
        this.category = category;
        this.recurrence = recurrence;
        this.description = description;
        this.creditCard = creditCard;
    }
}
