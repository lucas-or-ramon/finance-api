package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RecurrenceRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.RecurrenceResponse;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;

public interface RecurrenceMapper {

    static Recurrence mapToDomain(final RecurrenceRequest request) {
        return new Recurrence(request.type(), request.dueDate(), request.start().mapToDomain(), request.end().mapToDomain());
    }

    static RecurrenceResponse mapToResponse(final Recurrence recurrence) {
        return new RecurrenceResponse(recurrence.type(), recurrence.dueDate(), recurrence.start(), recurrence.end());
    }
}
