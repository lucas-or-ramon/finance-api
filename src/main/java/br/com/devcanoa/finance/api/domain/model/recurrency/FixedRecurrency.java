package br.com.devcanoa.finance.api.domain.model.recurrency;

import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import br.com.devcanoa.finance.api.adapter.inbound.mapper.RegistryMapper;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrencyEntity;
import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import org.bson.types.ObjectId;

import java.time.Period;
import java.util.List;

import static java.util.Objects.nonNull;

//public class FixedRecurrency<T extends RegistryEntity> implements RecurrencyRules<T> {

//    private final RegistryMapper<T> registryMapper;
//
//    public FixedRecurrency(RegistryMapper<T> registryMapper) {
//        this.registryMapper = registryMapper;
//    }
//
//    @Override
//    public List<T> creates(T registry) {
//        final var recurrency = getNewRecurrency(registry.getRecurrencyId());
//
//        return recurrency.start().datesUntil(recurrency.end().plusMonths(1), Period.ofMonths(1))
//                .map(date -> registryMapper.mapToDomain(new ObjectId().toString(), null))
//                .toList();
//    }
//
//    private RecurrencyEntity getNewRecurrency(RecurrencyEntity recurrencyEntity) {
//        if (isValid(recurrencyEntity)) {
//            return new RecurrencyEntity(recurrencyEntity.id(), recurrencyEntity.type(), recurrencyEntity.start(), recurrencyEntity.end(), null, null);
//        }
//        throw new FinanceException("Recurrency Is Not Valid");
//    }
//
//    private boolean isValid(RecurrencyEntity recurrencyEntity) {
//        final var start = recurrencyEntity.start();
//        final var end = recurrencyEntity.end();
//        return nonNull(start) && nonNull(end) && end.isAfter(start);
//    }
//}
