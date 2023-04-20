package br.com.devcanoa.finance.api.domain.model;

import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public class Expenditure extends Registry {

    public Expenditure(final String id, final Double value, final String description, final String creditCardId, final Recurrence recurrence) {
        super(id, value, description, creditCardId, recurrence);
    }
}
