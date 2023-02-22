package br.com.devcanoa.finance.api.mapper.request;

import br.com.devcanoa.finance.api.domain.request.RegistryRequest;
import br.com.devcanoa.finance.api.model.Expenditure;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class RequestExpenditureMapper implements RequestRegistryMapper<Expenditure> {

    @Override
    public Expenditure mapper(final Pair<ObjectId, RegistryRequest> requestPair) {
        final var second = requestPair.getSecond();
        final var recurrency = new RequestRecurrencyMapper().apply(second.recurrency());
        return new Expenditure(requestPair.getFirst(), second.date(), second.value(), second.description(), second.creditCardId(), recurrency);
    }
}
