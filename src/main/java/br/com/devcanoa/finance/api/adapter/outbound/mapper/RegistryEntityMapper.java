package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.ExpenditureEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrenceEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.domain.model.Registry;
import org.bson.types.ObjectId;

public interface RegistryEntityMapper<E extends RegistryEntity> {


    E mapToEntity(final Registry registry);

    final class Revenue implements RegistryEntityMapper<RevenueEntity> {
        @Override
        public RevenueEntity mapToEntity(final Registry registry) {
            return new RevenueEntity(
                    new ObjectId(registry.getId()),
                    registry.getValue(),
                    registry.getCategory(),
                    registry.getDescription(),
                    new ObjectId(registry.getCreditCardId()),
                    RecurrenceEntity.mapToEntity(registry.getRecurrence()));
        }
    }

    final class Expenditure implements RegistryEntityMapper<ExpenditureEntity> {
        @Override
        public ExpenditureEntity mapToEntity(final Registry registry) {
            final var creditCardId = registry.getCreditCardId() != null ? new ObjectId(registry.getCreditCardId()) : null;
            return new ExpenditureEntity(
                    new ObjectId(registry.getId()),
                    registry.getValue(),
                    registry.getCategory(),
                    registry.getDescription(),
                    creditCardId,
                    RecurrenceEntity.mapToEntity(registry.getRecurrence()));
        }
    }
}
