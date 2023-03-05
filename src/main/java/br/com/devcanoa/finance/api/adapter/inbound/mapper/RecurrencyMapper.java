package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RecurrencyRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.RecurrencyResponse;
import br.com.devcanoa.finance.api.domain.model.Recurrency;
import org.bson.types.ObjectId;

import static java.util.Objects.requireNonNullElse;

public interface RecurrencyMapper {

    static Recurrency mapToDomain(final RecurrencyRequest request) {
        final var id = requireNonNullElse(request.id(), new ObjectId().toString());
        return new Recurrency(id, request.type(), request.start(), request.end(), request.paid(), request.total());
    }

    static RecurrencyResponse mapToResponse(final Recurrency recurrency) {
        return new RecurrencyResponse(recurrency.id(), recurrency.type(), recurrency.start(), recurrency.end(), recurrency.paid(), recurrency.total());
    }
}
