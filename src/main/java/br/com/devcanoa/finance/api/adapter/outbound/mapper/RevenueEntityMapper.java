package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.domain.model.Revenue;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public class RevenueEntityMapper implements RegistryEntityMapper<RevenueEntity, Revenue>{

    @Override
    public Revenue mapToDomain(final RevenueEntity entity) {
        if (nonNull(entity)){
            final var creditCardId = entity.getCreditCardId();
            return new Revenue(
                    entity.getId().toString(),
                    entity.getValue(),
                    entity.getDescription(),
                    nonNull(creditCardId) ? creditCardId.toString() : null,
                    RecurrenceEntityMapper.mapToDomain(entity.getRecurrence()));

        }
        return null;
    }

    @Override
    public RevenueEntity mapToEntity(final Revenue registry) {
        final var creditCardId = registry.getCreditCardId();
        return new RevenueEntity(
                new ObjectId(registry.getId()),
                registry.getValue(),
                registry.getDescription(),
                nonNull(creditCardId) ? new ObjectId(creditCardId): null,
                RecurrenceEntityMapper.mapToEntity(registry.getRecurrence()));
    }

}
