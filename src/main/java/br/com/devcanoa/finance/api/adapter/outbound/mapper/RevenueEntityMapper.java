package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.domain.model.Revenue;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public class RevenueEntityMapper implements RegistryEntityMapper<RevenueEntity, Revenue>{

    @Override
    public Revenue mapToDomain(RevenueEntity entity) {
        if (nonNull(entity)){
            final var creditCardId = entity.getCreditCardId();
            return new Revenue(
                    entity.getId().toString(),
                    entity.getDate(),
                    entity.getValue(),
                    entity.getDescription(),
                    nonNull(creditCardId) ? creditCardId.toString() : null,
                    entity.getRecurrencyId());

        }
        return null;
    }

    @Override
    public RevenueEntity mapToEntity(Revenue registry) {
        final var creditCardId = registry.getCreditCardId();
        return new RevenueEntity(
                new ObjectId(registry.getId()),
                registry.getDate(),
                registry.getValue(),
                registry.getDescription(),
                nonNull(creditCardId) ? new ObjectId(creditCardId): null,
                RecurrencyEntityMapper.mapToEntity(registry.getRecurrency()));
    }

}
