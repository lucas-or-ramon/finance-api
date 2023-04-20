package br.com.devcanoa.finance.api.domain.model;

import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public class Registry {

    private final String id;
    private final Double value;
    private final String description;
    private final String creditCardId;
    private final Recurrence recurrence;

    public Registry(final String id, final Double value, final String description, final String creditCardId, final Recurrence recurrence) {
        this.id = id;
        this.value = value;
        this.recurrence = recurrence;
        this.description = description;
        this.creditCardId = creditCardId;
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

    public String getCreditCardId() {
        return creditCardId;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Installments getInstallments(final FinanceDate date) {
        return recurrence.getInstallments(date);
    }
}
