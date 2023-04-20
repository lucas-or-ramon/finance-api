package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrenceEntity;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public interface RecurrenceEntityMapper {

    static Recurrence mapToDomain(final RecurrenceEntity entity) {
        return new Recurrence(entity.type(), entity.dueDate(), entity.start(), entity.end());
    }

    static RecurrenceEntity mapToEntity(final Recurrence recurrence) {
        return new RecurrenceEntity(recurrence.type(), recurrence.dueDate(), recurrence.start(), recurrence.end());
    }
}
