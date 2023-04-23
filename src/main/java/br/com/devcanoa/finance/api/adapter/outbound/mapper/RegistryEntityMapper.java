package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.ExpenditureEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RevenueEntity;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.Registry;
import org.bson.types.ObjectId;

import static java.util.Objects.nonNull;

public interface RegistryEntityMapper<E extends RegistryEntity> {


    E mapToEntity(final Registry registry);

    default ObjectId creditCardId(final CreditCard creditCard) {
        return nonNull(creditCard) ? new ObjectId(creditCard.id()) : null;
    }

    final class Revenue implements RegistryEntityMapper<RevenueEntity> {

        @Override
        public RevenueEntity mapToEntity(final Registry registry) {
            return new RevenueEntity(
                    new ObjectId(registry.getId()),
                    registry.getValue(),
                    registry.getCategory(),
                    registry.getDescription(),
                    creditCardId(registry.getCreditCard()),
                    registry.getRecurrence());
        }
    }

    final class Expenditure implements RegistryEntityMapper<ExpenditureEntity> {

        @Override
        public ExpenditureEntity mapToEntity(final Registry registry) {
            return new ExpenditureEntity(
                    new ObjectId(registry.getId()),
                    registry.getValue(),
                    registry.getCategory(),
                    registry.getDescription(),
                    creditCardId(registry.getCreditCard()),
                    registry.getRecurrence());
        }
    }
}
