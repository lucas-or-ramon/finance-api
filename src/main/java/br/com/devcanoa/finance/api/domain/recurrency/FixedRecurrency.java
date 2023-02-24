package br.com.devcanoa.finance.api.domain.recurrency;

import br.com.devcanoa.finance.api.domain.exception.FinanceException;
import br.com.devcanoa.finance.api.domain.adapter.mapper.registry.RegistryMapper;
import br.com.devcanoa.finance.api.infra.adapter.entity.Recurrency;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;

import java.time.Period;
import java.util.List;

import static java.util.Objects.nonNull;

public class FixedRecurrency<T extends Registry> implements RecurrencyRules<T> {

    private final RegistryMapper<T> registryMapper;

    public FixedRecurrency(RegistryMapper<T> registryMapper) {
        this.registryMapper = registryMapper;
    }

    @Override
    public List<T> creates(T registry) {
        final var recurrency = getNewRecurrency(registry.getRecurrency());

        return recurrency.start().datesUntil(recurrency.end().plusMonths(1), Period.ofMonths(1))
                .map(date -> registryMapper.mapper(new ObjectId(), date, registry.getValue(), registry.getDescription(), registry.getCreditCardId(), recurrency))
                .toList();
    }

    private Recurrency getNewRecurrency(Recurrency recurrency) {
        if (isValid(recurrency)) {
            return new Recurrency(recurrency.id(), recurrency.type(), recurrency.start(), recurrency.end(), null, null);
        }
        throw new FinanceException("Recurrency Is Not Valid");
    }

    private boolean isValid(Recurrency recurrency) {
        final var start = recurrency.start();
        final var end = recurrency.end();
        return nonNull(start) && nonNull(end) && end.isAfter(start);
    }
}
