package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.ExpenditureEntity;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public class ExpenditureEntityMapper implements RegistryEntityMapper<ExpenditureEntity, Expenditure> {

    @Override
    public Expenditure mapToDomain(final ExpenditureEntity entity) {
        if (nonNull(entity)) {
            final var creditCardId = entity.getCreditCardId();
            return new Expenditure(
                    entity.getId().toString(),
                    entity.getDate(),
                    entity.getValue(),
                    entity.getDescription(),
                    nonNull(creditCardId) ? creditCardId.toString() : null,
                    RecurrencyEntityMapper.mapToDomain(entity.getRecurrencyId()));
        }
        return null;
    }

    @Override
    public ExpenditureEntity mapToEntity(final Expenditure registry) {
        final var credirCardId = registry.getCreditCardId();
        return new ExpenditureEntity(
                new ObjectId(registry.getId()),
                registry.getDate(),
                registry.getValue(),
                registry.getDescription(),
                nonNull(credirCardId) ? new ObjectId(credirCardId) : null,
                RecurrencyEntityMapper.mapToEntity(registry.getRecurrency()));
    }
}
