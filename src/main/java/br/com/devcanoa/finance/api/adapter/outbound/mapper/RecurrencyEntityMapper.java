package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrencyEntity;
import br.com.devcanoa.finance.api.domain.model.Recurrency;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public interface RecurrencyEntityMapper {

    static Recurrency mapToDomain(final RecurrencyEntity entity) {
        return nonNull(entity)
                ? new Recurrency(entity.id().toString(), entity.type(), entity.start(), entity.end(), entity.paid(), entity.total())
                : null;
    }

    static RecurrencyEntity mapToEntity(final Recurrency recurrency) {
        return new RecurrencyEntity(new ObjectId(recurrency.id()), recurrency.type(), recurrency.start(), recurrency.end(), recurrency.paid(), recurrency.total());
    }
}
